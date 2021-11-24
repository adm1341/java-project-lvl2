### Hexlet tests and linter status:
[![Actions Status](https://github.com/adm1341/java-project-lvl2/workflows/hexlet-check/badge.svg)](https://github.com/adm1341/java-project-lvl2/actions)
[![Java CI](https://github.com/adm1341/java-project-lvl2/actions/workflows/main.yml/badge.svg)](https://github.com/adm1341/java-project-lvl2/actions/workflows/main.yml)
<a href="https://codeclimate.com/github/adm1341/java-project-lvl2/maintainability"><img src="https://api.codeclimate.com/v1/badges/0f0296dae92ce44dc15d/maintainability" /></a>
<a href="https://codeclimate.com/github/adm1341/java-project-lvl2/test_coverage"><img src="https://api.codeclimate.com/v1/badges/0f0296dae92ce44dc15d/test_coverage" /></a>
<br>
Вычислитель отличий – программа, определяющая разницу между двумя структурами данных. Это популярная задача, для решения которой существует множество онлайн-сервисов, например: http://www.jsondiff.com/. Подобный механизм используется при выводе тестов или при автоматическом отслеживании изменении в конфигурационных файлах.
<br><br>
Возможности утилиты:
<br>
```
Поддержка разных входных форматов: yaml и json
Генерация отчета в виде plain text, stylish и json
```

Пример использования:
```
# формат plain
$ ./app --format plain path/to/file.yml another/path/file.json

Property 'follow' was added with value: false
Property 'baz' was updated. From 'bas' to 'bars'
Property 'group2' was removed

# формат stylish
$ ./app filepath1.json filepath2.json

{
+ follow: false
+ numbers: [1, 2, 3]
  setting1: Value 1
- setting2: 200
- setting3: true
+ setting3: {key=value}
+ setting4: blah blah
  }
```