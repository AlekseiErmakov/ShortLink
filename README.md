# ShortLink

ShortLink cr.
* POST /api/generate {original : "http://somelink.com"} - в ответ придет сгенерированная ссылка
* GET /api/stats/{link} - полная статистика сохраненного ресурса
* GET /api/{link} - редирект на сохраненный ресурс
* GET /api/stats?page=1&count=1 - вернет массив сохранненных ресурсов, отсортированный по рейтингу
