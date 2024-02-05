# Отчет о проделанной работе по TDD.

Я решил разработать по TDD форму т.к для начала это проще всего. В моем проекте используется Future Sliced Design - есть слои shared, entities, features.  
Проще всего конечно же тестировать компоненты из слоя shared. Entites собираются из shared, а features собираются из shared и entities.  
Есть еще слой Widgets, который можем импортировать в себя shared, entities, features.  
Но я начал с shared.

Вначале нужно было инициализировать среду тестирования. Это было не просто. Легко тестировать обычные функции, но тестировать компоненты React сложнее.  
Требовалось сконфигурировать Typescript, Babel.

Я пытался описывать тесты таким образом, буд-то я ничего не знаю про Javascript или React.  
Например: у меня был компонент DebounceInput. Он изменяет значения в себе, но колбэк на изменение значения вызывает не сразу.  
Таким образом я описал для него тест:

```typescript
describe("Спецификация компонента <DebounceInput />", () => {
  test("Если компоненту передать дефолтное значение, то он будет вмонтирован с вместе с этим значением", async () => {
    const DEFAULT_VALUE = "DEFAULT_VALUE";

    render(<DebounceInput value={DEFAULT_VALUE} onQueryChange={() => null} />);

    const input = document.querySelector("input")!;

    expect(input).toBeInTheDocument();

    expect(input.value).toBe(DEFAULT_VALUE);
  });

  test("Если что-то ввести в инпуте, то колбэк вызовется только через заданное время в debounceTime", async () => {
    const fn = vi.fn();

    render(<DebounceInput value="" onQueryChange={fn} debounceTime={300} />);

    const input = document.querySelector("input")!;

    await userEvent.type(input, "TEST");

    // колбэк не вызвался
    expect(fn.mock.calls.length).toBe(0);

    await wait(300);

    // колбэк вызвался
    expect(fn.mock.calls.length).toBe(1);
  });
});
```

После того, как я описал такое поведение, я имел 2 красных теста в консоли. Тогда я приступил к реализации компонента DebounceInput. И писал его реализацию до тех пор, пока мои тесты не превратились в зеленые.  
Но тогда, я понял, что мой код следует тестам. Когда я это осознал, я отключил тесты и стал писать компонент так, как должен вести себя DebounceInput логически.  
Тобишь так, как я бы ожидал его поведение.  
При таком подходе мой код инпута немного видоизменился.

Так я продолжил писать тесты и реализовывать shared-компоненты. Дополнительно я стал прогонять shared компоненты через AFL тесты, которые научился интегрировать в прошлом уроке.

Следующим инсайдом я открыл, что тесты можно писать при помощи AI помощника. Я имел shared компонент Dropdown.  
Так я попросил помощника написать мне тесты к нему:

---

Напиши unit тесты к компонету <Dropdown />
он принимает следующие пропсы:

```javascript
{
  isOpen: boolean,
  children: ReactNode,
  className: string
}
```

Компонент должен быть раскрыт, если isOpen true и закрыт, если isOpen false

---

Я получил такой результат:

```javascript
describe("<Dropdown />", () => {
  it("рендерит компонент с закрытым состоянием", () => {
    render(<Dropdown isOpen={false}>Контент</Dropdown>);
    const dropdownContent = screen.queryByText("Контент");
    expect(dropdownContent).toBeNull();
  });

  it("рендерит компонент с открытым состоянием", () => {
    render(<Dropdown isOpen={true}>Контент</Dropdown>);
    const dropdownContent = screen.getByText("Контент");
    expect(dropdownContent).toBeInTheDocument();
  });

  it("добавляет класс компоненту", () => {
    render(
      <Dropdown isOpen={true} className="custom-class">
        Контент
      </Dropdown>
    );
    const dropdownContent = screen.getByText("Контент");
    expect(dropdownContent).toHaveClass("custom-class");
  });

  it("закрывает компонент при клике на документ", () => {
    render(<Dropdown isOpen={true}>Контент</Dropdown>);
    const dropdownContent = screen.getByText("Контент");
    expect(dropdownContent).toBeInTheDocument();
    userEvent.click(document.body); // клик за пределами компонента
    expect(dropdownContent).not.toBeInTheDocument();
  });
});
```

Заметно, что AI пишет тесты на уровне реализации (2-м уровне). "Рендерит компонент с закрытым состоянием". Это не правильно.  
В идеали, при написании тестов мы не должны знать что значит "рендерит".  
Мы можем лишь понимать: есть контент на странице или нет.  
С таким посылом я видоизменил тесты, которые предложил мне помощник.

При тестировании компонентов на уровне feautres мне потребовался фейковый бэкенд.  
Я использовал библиотеку https://mswjs.io/ для фейкового сервера.
Тесты запускал через https://vitest.dev/.
