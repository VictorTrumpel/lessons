`hideNotification` -> `hide` - т.к. является метододом класса `Notifiation` получается (`notification.hide`)

`setDate` -> `setLocalStoradgeDate` - устанавливает дату в локал сторадж

`mergeValues` -> `mergeDefaultAndStoradgeValues` - метод берет значения формы из локал стродажа и соединяет их с дефолтными значениями формы. Название несуразное, но лучше чем `mergeValues`. Улучшаю в след. пункте.

`mergeDefaultAndStoradgeValues` -> делим на 2 метода: 
  1. `getStoradgeValues` - метод для получения значений из лока-стора
  2. `getMergedFormValues(v1, v2)` - метод, который возвращает значение формы, которое получается при слиянии 2-х значений.

`currentDate` -> `getTodayDate` - метод для получения сегодняшней даты

`clearLocalStoradge` -> `clearForm` - метод находится в классе `LocalFormStoradge`

`calc` -> `calcDays` - из класса `MeetCalendar`

`nextPage` -> `next` - урощение т.к. находится в классе `Paginator`

`regulateActions` -> `sortActionsBySignerPost` метод в классе `ActionsRegulator` который сортирует очереди подписания в зависимости от должности подписанта

`sortActionsBySignerPost` -> `sortActionsBy(parametr: 'post' | 'age')` передаем типизированый параметр сортивки и реализуем каждую сортировку отдельно в приватных методах в классе `ActionsRegulator` например (`sortByAge`) в методе `sortByAge` понятно, что он сортирует именно порядок подписания т.к. находится в классе `ActionsRegulator` и является приватным.
Заодно меняем имя класса `ActionsRegulator` -> `ActionsSorter` т.к. единственное назначение этого класса по-разному сортировать подписи

`unblockSigner(id: string)` -> `unblock(id: string)` т.к. метод находится в классе `SginersBlocker` 

`changeSignerName` -> `setName` метод находится в классе `SignerProfile`


