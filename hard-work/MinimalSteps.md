# Отчет о минимизации шагов измерений.

### Пример 1.

---

Получившийся код:

---

```typescript
/*
  Сервис по сохранению чек-листов в indexDB.
  Хранилище чек-листов в indexDB называется finishing
  Этот сервис используется в FinishingController когда нужно отвязать сохранение чек-листов от бэка.
*/
export class FinishingServiceLocalStore implements IFinishingService {
  async create(
    createFinishingDTO: CreateFinishingDTO
  ): Promise<GetFinishingDTO> {
    const allFinishingList = await this.getAll();

    const finishingId = allFinishingList.length + 1;

    const finishing = this.convertToFinishing(createFinishingDTO, finishingId);

    await addDataInDB("finishing", finishing);

    return this.convertToGetFinishingDTO(finishing);
  }

  async getPage({
    roomUuid,
    page = 1,
    size = 10,
  }: {
    roomUuid?: string;
    page?: number;
    size?: number;
  }) {
    const finishing = await this.getAll();

    let queryFinishingList = finishing;

    if (roomUuid) {
      queryFinishingList = finishing.filter(
        (finishing) => finishing.roomUUID == roomUuid
      );
    }

    const getQueryFinishingList = queryFinishingList.map(
      this.convertToGetFinishingDTO.bind(this)
    );

    const response: PageType<GetFinishingDTO> = this.wrapToPage(
      getQueryFinishingList,
      page,
      size
    );

    return response;
  }

  async updateFinishingItem(updateFinishingItemDTO: UpdateFinishingItemDTO) {
    const allFinishing = await this.getAll();

    const updateFinishingUuid = updateFinishingItemDTO.uuid;

    const finishingNeedUpdate = allFinishing.find((finishing, idx) => {
      return finishing.finishingList.find(({ uuid }) => {
        return uuid === updateFinishingUuid;
      });
    });

    if (!finishingNeedUpdate) return;

    const finishingItemNeedUpdate = finishingNeedUpdate.finishingList.find(
      ({ uuid }) => uuid === updateFinishingUuid
    );

    if (!finishingItemNeedUpdate) return;

    finishingItemNeedUpdate.factUnits = updateFinishingItemDTO.factUnits;
    finishingItemNeedUpdate.status = updateFinishingItemDTO.status;
    finishingItemNeedUpdate.comment = updateFinishingItemDTO.comment;

    await deleteDataFromDB("finishing", finishingNeedUpdate.uuid || "");
    await addDataInDB("finishing", finishingNeedUpdate);
  }

  private getAll(): Promise<Finishing[]> {
    return new Promise((resolve) => {
      const request = indexedDB.open(DB_NAME, versionDB);

      request.onsuccess = () => {
        const db = request.result;
        const tx = db.transaction("finishing", "readonly");
        const store = tx.objectStore("finishing");
        const res = store.getAll();

        res.onsuccess = () => {
          resolve(res.result);
        };
      };
    });
  }

  private convertToFinishing(
    createFinishingDTO: CreateFinishingDTO,
    finishingId: number
  ): Finishing {
    const finishing: Finishing = {
      ...createFinishingDTO,

      id: finishingId,
      uuid: uuidv4(),
      dateCreate: new Date(createFinishingDTO.dateCreate || ""),
      finishingList: createFinishingDTO.finishingList.map(
        (createFinishingItem, idx) =>
          this.convertToFinishingItem(createFinishingItem, finishingId, idx)
      ),
    };

    return finishing;
  }

  private convertToFinishingItem(
    createFinishingItem: CreateFinishingItemDTO,
    finishingId: number,
    finishingItemId: number
  ): FinishingItem {
    const finishingItem = new FinishingItem();

    finishingItem.id = finishingItemId;
    finishingItem.uuid = uuidv4();
    finishingItem.type = createFinishingItem.type;
    finishingItem.name = createFinishingItem.name;
    finishingItem.mark = createFinishingItem.mark;
    finishingItem.units = createFinishingItem.units;
    finishingItem.factUnits = createFinishingItem.factUnits;
    finishingItem.status = createFinishingItem.status;
    finishingItem.comment = createFinishingItem.comment;
    finishingItem.finishingId = finishingId;

    return finishingItem;
  }

  private convertToGetFinishingDTO(finishing: Finishing): GetFinishingDTO {
    const getFinishingDTO = new GetFinishingDTO();

    getFinishingDTO.id = finishing.id;
    getFinishingDTO.uuid = finishing.uuid;
    getFinishingDTO.roomName = finishing.roomName;
    getFinishingDTO.roomUUID = finishing.roomUUID;
    getFinishingDTO.modelFile = finishing.modelFile;
    getFinishingDTO.projectId = finishing.projectId;
    getFinishingDTO.containerId = finishing.containerId;
    getFinishingDTO.dateCreate = finishing.dateCreate?.toDateString() || null;
    getFinishingDTO.finishingList = finishing.finishingList.map(
      this.convertToGetFinishingItemDTO
    );

    return getFinishingDTO;
  }

  private convertToGetFinishingItemDTO(
    finishingItem: FinishingItem
  ): GetFinishingItemDTO {
    return { ...finishingItem };
  }

  private wrapToPage<Data extends unknown[]>(
    content: Data,
    page: number,
    size: number
  ) {
    const contentOnPage = content.slice((page - 1) * size, (page + 1) * size);

    const response: PageType<Data[number]> = {
      content: contentOnPage,
      empty: content.length === 0,
      first: page === 1,
      last: contentOnPage.length === content.length,
      number: NaN,
      numberOfElements: content.length,
      pageable: {
        offset: NaN,
        pageNumber: NaN,
        pageSize: NaN,
        paged: true,
        sort: {
          empty: content.length === 0,
          sorted: false,
          unsorted: true,
        },
        unpaged: false,
      },
      size,
      sort: {
        empty: false,
        sorted: false,
        unsorted: false,
      },
      totalElements: content.length,
      totalPages: NaN,
    };

    return response;
  }
}
```

---

Получившиеся тесты:

---

```typescript
async function requestAllFinishing(): Promise<Finishing[]> {
  return new Promise((resolve) => {
    const request = indexedDB.open(DB_NAME, versionDB);

    request.onsuccess = () => {
      const db = request.result;
      const tx = db.transaction(Stores.Finishing, "readonly");
      const store = tx.objectStore(Stores.Finishing);
      const res = store.getAll();

      res.onsuccess = () => {
        resolve(res.result);
      };
    };
  });
}

async function clearDB() {
  return new Promise((resolve) => {
    const request = indexedDB.open(DB_NAME, versionDB);

    request.onsuccess = function (event) {
      const db = request.result;

      // Открытие транзакции для выполнения операции удаления
      const transaction = db.transaction(Stores.Finishing, "readwrite");

      const store = transaction.objectStore(Stores.Finishing);

      store.clear();

      console.log("Хранилище успешно удалено из базы данных");

      resolve(null);
    };
  });
}

initDB();

describe("Спецификация класса FinishingServiceLocalStore", () => {
  test("Метод create создает чек-лист в IndexDB", async () => {
    await clearDB();

    const finishingService = new FinishingServiceLocalStore();

    const createFinishingDTO = new CreateFinishingDTO();
    createFinishingDTO.roomName = "TEST ROOM NAME";
    createFinishingDTO.roomUUID = "TEST ROOM UUID";

    await finishingService.create(createFinishingDTO);

    const requestedFinishings = await requestAllFinishing();

    expect(requestedFinishings.length).toBe(1);

    expect(requestedFinishings[0].roomUUID).toBe("TEST ROOM UUID");
  });

  test("Метод create создает чек-лист в IndexDB, который можно вернется в пагинации при вызове метода getPage", async () => {
    await clearDB();

    const finishingService = new FinishingServiceLocalStore();

    const createFinishingDTO = new CreateFinishingDTO();
    createFinishingDTO.roomName = "TEST ROOM NAME";
    createFinishingDTO.roomUUID = "TEST ROOM UUID";

    await finishingService.create(createFinishingDTO);

    const finishingPage = await finishingService.getPage({
      roomUuid: createFinishingDTO.roomUUID,
    });

    expect(finishingPage.content.length).toBe(1);

    expect(finishingPage.content[0].roomUUID).toBe("TEST ROOM UUID");
  });

  test("При создании чек-листа через метод create, чек-листы сохряняются с id который автоинкрементируется на 1", async () => {
    await clearDB();

    const finishingService = new FinishingServiceLocalStore();

    const createFinishingDTO = new CreateFinishingDTO();
    createFinishingDTO.roomName = "TEST ROOM NAME 1";
    createFinishingDTO.roomUUID = "TEST ROOM UUID 1";

    await finishingService.create(createFinishingDTO);

    createFinishingDTO.roomName = "TEST ROOM NAME 2";
    createFinishingDTO.roomUUID = "TEST ROOM UUID 2";

    await finishingService.create(createFinishingDTO);

    createFinishingDTO.roomName = "TEST ROOM NAME 3";
    createFinishingDTO.roomUUID = "TEST ROOM UUID 3";

    await finishingService.create(createFinishingDTO);

    const allFinishing = await requestAllFinishing();

    const f1 = allFinishing.find((f) => f.roomName == "TEST ROOM NAME 1")!;
    const f2 = allFinishing.find((f) => f.roomName == "TEST ROOM NAME 2")!;
    const f3 = allFinishing.find((f) => f.roomName == "TEST ROOM NAME 3")!;

    expect(f1.id).toBe(1);
    expect(f2.id).toBe(2);
    expect(f3.id).toBe(3);
  });

  test("Метод updateFinishingItem обновляет элемент чек-листа", async () => {
    await clearDB();

    const finishingService = new FinishingServiceLocalStore();

    const finishingItem = new FinishingItem();
    finishingItem.uuid = uuid();
    finishingItem.status = "AWAIT";
    finishingItem.comment = "";

    const createFinishingDTO = new CreateFinishingDTO();
    createFinishingDTO.roomName = "TEST ROOM NAME";
    createFinishingDTO.roomUUID = "TEST ROOM UUID";
    createFinishingDTO.finishingList = [finishingItem];

    await finishingService.create(createFinishingDTO);

    let [finishing] = await requestAllFinishing();

    const savedFinishingItem = finishing.finishingList[0];

    expect(savedFinishingItem.status).toBe("AWAIT");

    // изменяю статус у составляющей чек-листа
    savedFinishingItem.status = "APPROVE";

    await finishingService.updateFinishingItem(savedFinishingItem);

    let [updateFinishing] = await requestAllFinishing();

    // проверяю, изменился ли статус на 'APPROVE'
    expect(updateFinishing.finishingList[0].status).toBe("APPROVE");
  });
});
```

---

Как выполнял:

Изначально, я описал интерфейс IFinishingService.  
Затем я начал писать тесты, стараясь описывать их максимально оторванно от кода.

Я описал первый тест, который проверял метод _create_ и начал писать саму реализацию.  
Изначально мне приходилось о-о-очень часто и о-о-очень много стирать кода.  
Это было из-за технических особенностей, связанных с тем, что мой код взаимодействует с indexDB (база данных в браузере) и нужно было правильно настроить среду.  
Я изначально не понимал, что у меня идут ошибки именно из-за неправильно конфигурации тестов.

После того, как среда была полностью настроена я смог спокойно двигаться по тестам.  
У меня получался аналог зеленого-красного рефакторинга.

В данном примере, мне было сложно разбить код на маленькие порции, т.к. я хотел сохранить многие методы приватными.

---

### Пример 2.

---

Получившийся код:

---

```typescript
type PropsType = {
  unitsName?: string;
  value?: string;
  onChange?: (e: ChangeEvent<HTMLInputElement>) => void;
  placeholder?: string;
  allowClear?: { clearIcon: JSX.Element };
};

export const InputNumber = ({
  value,
  onChange,
  unitsName,
  placeholder,
  allowClear,
}: PropsType) => {
  const inputRef = useRef<HTMLInputElement | null>(null);
  const containerRef = useRef<HTMLDivElement | null>(null);

  useMemo(() => {
    value = parseRusNumberFormatFromString(value);
  }, []);

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;

    const numberStrWithReplacedCommas = value.replace(",", ".");

    if (Number.isNaN(+numberStrWithReplacedCommas)) return;

    const numberStrRusFormat = value.replace(".", ",").replaceAll(/\s/g, "");

    e.target.value = numberStrRusFormat;

    onChange?.(e);
  };

  const handleContainerClick = () => {
    inputRef.current?.focus();
  };

  const handleClear = () => {
    const inputHtml = inputRef.current;
    if (!inputHtml) return;
    inputHtml.value = "";
    onChange?.({ target: inputHtml } as ChangeEvent<HTMLInputElement>);
  };

  useEffect(() => {
    const inputHtml = inputRef.current;
    const containerHtml = containerRef.current;

    if (!inputHtml || !containerHtml) return;

    inputHtml.onfocus = () => {
      containerHtml.classList.add("focused");
    };

    inputHtml.onblur = () => {
      containerHtml.classList.remove("focused");
    };
  }, []);

  return (
    <div
      ref={containerRef}
      className="input-number-container"
      onClick={handleContainerClick}>
      <AutosizeInput
        inputRef={(input) => {
          inputRef.current = input;
        }}
        autoComplete="off"
        placeholder={placeholder}
        value={value}
        onChange={handleChange}
      />
      <span className="unit-name">{unitsName}</span>
      <span onClick={handleClear} className="clear-icon-container">
        {Boolean(value) && allowClear?.clearIcon}
      </span>
    </div>
  );
};
```

---

Получившиеся тесты:

---

```typescript
describe("Спецификация компонента <InputNumber />", () => {
  test('Если компоненту передать значение "12,12" то он отобразит 12,12', () => {
    render(<InputNumber value="12,12" />);

    const input = document.querySelector("input")!;

    expect(input.value).toBe("12,12");
  });

  test('Если компоненту передать значение "12.12" то он отобразит "12,12"', () => {
    render(<InputNumber value="12.12" />);

    const input = document.querySelector("input")!;

    expect(input.value).toBe("12,12");
  });

  test('Если компоненту передать значение "", то он отобразит ""', async () => {
    render(<InputNumber value="" />);

    const input = document.querySelector("input")!;

    expect(input.value).toBe("");
  });

  test('Если компоненту передать значение "12.12", а после ввести " ", то значение не изменится', async () => {
    const Component = () => {
      const [value, setValue] = useState("12.12");
      return (
        <InputNumber value={value} onChange={(v) => setValue(v.target.value)} />
      );
    };

    render(<Component />);

    const input = document.querySelector("input")!;

    await userEvent.type(input, " ");

    expect(input.value).toBe("12,12");
  });

  test("Если ввести не числовой символ, то значение в поле не изменится", async () => {
    const Component = () => {
      const [value, setValue] = useState("");
      return (
        <InputNumber value={value} onChange={(v) => setValue(v.target.value)} />
      );
    };

    render(<Component />);

    const input = document.querySelector("input")!;

    const nonNumberChars = [
      "a",
      "b",
      "куц",
      ",",
      ".",
      "bb",
      "ss",
      " ",
      "§",
      "+",
      "-",
      "_",
      ">",
      ",",
      "y",
      "BB",
      "Array",
    ];

    for (const char of nonNumberChars) {
      await userEvent.type(input, char);
    }

    expect(input.value).toBe("");
  });

  test('Если в поле есть уже есть "1" и ввести ".", то отобразится "1,"', async () => {
    const Component = () => {
      const [value, setValue] = useState("1");
      return (
        <InputNumber value={value} onChange={(v) => setValue(v.target.value)} />
      );
    };

    render(<Component />);

    const input = document.querySelector("input")!;

    await userEvent.type(input, ".");

    expect(input.value).toBe("1,");
  });

  test('Если в поле есть уже есть "1," и ввести ".", то отобразится "1,"', async () => {
    const Component = () => {
      const [value, setValue] = useState("1,");
      return (
        <InputNumber value={value} onChange={(v) => setValue(v.target.value)} />
      );
    };

    render(<Component />);

    const input = document.querySelector("input")!;

    await userEvent.type(input, ".");

    expect(input.value).toBe("1,");
  });
});
```

---

Во втором примере я тестировал компонент, созданный при помощи библиотеки React.
В этом примере мне было легче бить код на порции.

Вначале я реализовал стандартный ввод только чисел.  
Затем перешел на автозамену запятой на точку.  
Затем удаление букв.  
И т.д.

Если продолжать дописывать этот компонент, то можно небольшими порциями вводить новые тест-кейсы, и потихоньку докручивать функционал.

---
