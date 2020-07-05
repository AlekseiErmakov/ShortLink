# ShortLink

ShortLink cr.
* POST /api/generate {original : "http://somelink.com"} - в ответ придет сгенерированная ссылка формата /api/l/{uuid}
* GET /api/stats/l/{uuid} - полная статистика сохраненного ресурса
* GET /api/l/{uuid} - редирект на сохраненный ресурс
* GET /api/stats?page=1&count=1 - вернет массив сохранненных ресурсов, отсортированный по рейтингу
