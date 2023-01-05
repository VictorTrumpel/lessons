**7.1**

*isFound* -> found

*isDone* -> done

*hasError* -> error

*isSuccess* -> success

*signed* -> isSigned (подписан)

*7.2*

```javascript
success = sendDocument() // функция, которая отправляет документ и возвращает true - если успешно отправлен и false в обратном случае

error = isValidForm() // функция проверяет валидность заполняемой формы

function toggleNextTusk(done) {
  if (done) {
    // если предыдущая задача выполнена - переключить таску

    return;
  }

  // если не выполнена, сделать что-то другое
}
```

*7.3*

```javascript
for (let docIdx = 0; docIdx < docLength; docIdx++) {
  for (let signerIdx = 0; signerIdx < signersLength; signerIdx++) {
    ...
  }
}
// у нас есть массив документов, в каждом документе есть массив подписантов и нужно пробежаться по каждому подписанту в каждом документе, как вариант
```

*7.4*

inProcessing - pause // в процессе (подписания) - на паузе. (Речь о процессе/паузе подписания документа)

activeInput - disabledInput // активный инпут - заблокированный инпут

*7.5*

```javascript
  let isAllDone = true - временная переменная, проверят, все ли подписанты во всех документах закончили свое подписание
      
  for (let docIdx = 0; docIdx < docLength; docIdx++) {
    if (!signers[signerIdx].done) {
      // если нашли, что хотя бы в одном документе один не закончил - перерываем весь цикл
      break
    }
    for (let signerIdx = 0; signerIdx < signersLength; signerIdx++) {
      if (!signers[signerIdx].done) { 
        // если нашли, что хотя бы в одном документе один не закончил - перерываем весь цикл
        isAllSignersDone = false
        break
      }
    }
  }

  if (isAllSignersDone) {
    // что-то делаем
  }
```


*isAllDone* - лучше переименовать на *isAllSignersDone*

и можно полностью избавиться от временной переменной если вынести 2 цикла в отдельную функцию и делать ранний выход

```javascript
const isAllSignersDone = () => {
  for (let docIdx = 0; docIdx < docLength; docIdx++) {
    for (let signerIdx = 0; signerIdx < signersLength; signerIdx++) {
      if (!signers[signerIdx].done) { 
        // если нашли, что хотя бы в одном документе один не закончил - возвращаем false
        
        return false
      }
    }
  }

  return true
}

// где-то в коде...

if (isAllSignersDone()) {
  // что-то делаем
}
```
