<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        label {
            font-weight:bold;
        }

        #padding {

            padding:25px;
            background:#d9d9d9;
            margin:auto;
            width:50%;
            position:relative;
        }
    </style>
    <title>Login</title>
</head>
<body>
    <br><br>
    <h1 style="text-align:center;">Benvenuto</h1>
    <br><br>
    <div id="padding" class="col-md-3">
        <form action="login" method="POST" class="needs-validation" novalidate>
            <div class="form-group">
                <label>Username:</label>
                <input type="text" class="form-control" id="user" placeholder="username" name="username" required />
            </div>
            <div class="form-group">
                <label>Password:</label>
                <input type="password" class="form-control" id="password" placeholder="password" name="password" required />
            </div>
            <button type="submit" class="btn btn-success" style="width:25%;">Entra</button>
            <button type="reset" class="btn btn-danger">Annulla</button>
        </form>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <script>
        // Disable form submissions if there are invalid fields
        var empty = false;
        (function() {
            'use strict';
            window.addEventListener('load', function() {
                // Get the forms we want to add validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if(form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                            empty = true;
                        }
                        if(empty === true) {
                            form.classList.add('was-validated');
                            empty = false;
                        }
                    }, false);
                });
            }, false);
        })();
    </script>
</body>
</html>
