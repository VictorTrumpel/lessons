# Модульное противоречие

Существуют ли ситуации, когда связи между модулями должны делаться публичными?

Да, существуют. Это нужно, для:

- интеграции модулей друг с другом
- расширяемости
- интерфейсы и АПИ
- общедоступных данных

Какие метрики вы бы предложили для количественной оценки принципов организации модулей?
Если вы разрабатывали программы, в которых было хотя бы 3-5 классов, как бы вы оценили их модульность по этим метрикам?

- Coupling - оценивает степень зависимости между модулями. Низкое сцепление указывает на то, что модули слабо связаны и изменения в одном модуле минимально влияют на другие.
- Количество интерфейсов - Количество публичных методов и свойств, предоставляемых модулем. Слишком большое количество интерфейсов может указывать на плохую абстракцию и сложность в использовании.
- Размер модуля - Измеряет количество строк кода или функциональных единиц внутри модуля. Слишком большие модули могут быть сложными для понимания и поддержки.
- Частота изменений - Как часто модуль изменяется по сравнению с другими модулями. Высокая частота изменений может указывать на нестабильность или плохую декомпозицию.
- Покрытие тестами - Процент кода модуля, покрытого тестами. Высокое покрытие указывает на хороший дизайн и легкость тестирования.

Таким образом все можно оценить в цифрах. Как оценить в цифрах Coupling я пока что не знаю.
