# ShortLink

ShortLink cr.
* POST /webapi/generate {original : "http://somelink.com"} - в ответ придет объект с сгенерированной ссылкой
* GET /webapi/stats/{link} - полная статистика сохраненного ресурса
* GET /webapi/{link} - редирект на сохраненный ресурс
* GET /webapi/stats?page=1&count=1 - вернет массив сохранненных ресурсов, отсортированный по рейтингу
