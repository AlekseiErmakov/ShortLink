<html>
<head>
    <title>Company page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
    <div class="container mx-auto">
        <h2 class="mt-5">Short link creation</h2>
        <form class="link-form" method="post" action="/webapi/generate">
            <div class="form-group">
                <label for="original">Your link</label>
                <input type="text" class="form-control" id="original" name="original" aria-describedby="originalHint">
                <small id="originalHint" class="form-text text-muted">Use generated link for to access the resource</small>
            </div>
            <button type="button" class="btn btn-info">Submit</button>
            <div class="mt-4 result-link"></div>
        </form>
    </div>
    <script>
        var form = document.querySelector('.link-form');
        var btn = form.querySelector('button');
        var result = document.querySelector('.result-link');
        btn.addEventListener('click', () => {
            var link = form.querySelector('#original').value;
            if (link) {
                var xhr = new XMLHttpRequest();
                var json = JSON.stringify({
                    original: link
                });
                xhr.addEventListener('load', () => {
                    if (xhr.status === 200) {
                        result.innerHTML = xhr.response;
                    } else {
                        console.log('Status: ' + xhr.status + ' ' + xhr.statusText);
                    }
                });
                xhr.open("POST", '/webapi/generate', true);
                xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                xhr.send(json);
            }
        });
    </script>
</body>
</html>
