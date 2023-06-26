<#import "blocks/template.ftlh" as t>
<@t.template>
    <span class="alert" id="message" role="alert"></span>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-6 col-md-8 col-sm-10">
                <div class="card-body">
                    <h3 class="card-title text-center mb-4">Профиль пользователя</h3>
                    <div class="form-group">
                        <h5 class="mb-3">Электронная почта</h5>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <hr>
                    <h5 class="mb-3">Изменить пароль</h5>
                    <div class="form-group">
                        <label for="newPassword">Новый пароль</label>
                        <div class="input-group">
                            <input type="password" class="form-control" id="newPassword" name="newPassword"
                                   minlength="8">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="confirmNewPassword">Подтвердите новый пароль</label>
                        <div class="input-group">
                            <input type="password" class="form-control" id="confirmNewPassword"
                                   name="confirmNewPassword" minlength="8">
                        </div>
                    </div>
                    <h5 class="mb-3">Подтвердите пароль</h5>
                    <div class="form-group">
                        <label for="currentPassword">Текущий пароль</label>
                        <div class="input-group">
                            <input type="password" class="form-control" id="currentPassword" name="currentPassword"
                                   required>
                        </div>
                    </div>
                    <div class="form-group mt-3">
                        <button onclick="sendPassword()" class="btn btn-primary btn-block" style="width: 100%;">Сохранить изменения</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        let emailField = document.getElementById("email");
        console.log("${email}");
        emailField.value = "${email}";

        function sendPassword() {
            let message = document.getElementById("message");
            message.textContent = "";
            message.classList.remove("alert-danger");
            message.classList.remove("alert-success");
            const email = document.getElementById("email").value;
            let password = document.getElementById("newPassword").value; // The user's password
            let confirmPassword = document.getElementById("confirmNewPassword").value;
            let oldPassword = document.getElementById("currentPassword").value;

            let alertMessage = checkEmail(email);

            if (alertMessage !== null) {
                alert("Введеные пароли не совпадают");
                return;
            }

            if (password !== confirmPassword) {
                alert("Введеные пароли не совпадают");
                return;
            }

            alertMessage = checkPassword(password);
            if (alertMessage !== null && password != null && password != "") {
                alert(alertMessage);
                return;
            }
            let sendData = {
                email: email,
                password: password,
                oldPassword: oldPassword
            };
            fetch('/profile/save', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'X-CSRF-Token': "${_csrf.token}"
                },
                body: JSON.stringify(sendData)
            }).then(response => {
                if (!response.ok) {
                    // Handle error response
                    console.log("Failed to update profile");
                    message.classList.add("alert-danger");
                    message.textContent = "Произошла ошибка, данные не изменены, проверьте пароль"
                } else {
                    console.error("Profile updated successfully");
                    message.classList.add("alert-success");
                    message.textContent = "Данные успешно изменены";
                    // Perform any necessary actions, such as displaying an error message or handling specific error cases
                }
                document.getElementById("newPassword").value = "";
                document.getElementById("confirmNewPassword").value = "";
                document.getElementById("currentPassword").value = "";
            });
        }

        function checkPassword(password) {
            const minLength = 8;
            const maxLength = 128;
            const uppercaseRegex = /[A-ZА-ЯЁ]/;
            const lowercaseRegex = /[a-zа-яё]/;
            const digitRegex = /[0-9]/;
            const alphanumericRegex = /^[a-zA-Zа-яА-ЯёЁ0-9]+$/;
            const spaceRegex = /\s/;

            if (password.length < minLength || password.length > maxLength) {
                return "Длина пароля должна быть от 8 до 128 символов.";
            }

            if (!uppercaseRegex.test(password) || !lowercaseRegex.test(password)) {
                return "Пароль должен содержать как минимум одну заглавную и одну строчную букву.";
            }

            if (!digitRegex.test(password)) {
                return "Пароль должен содержать как минимум одну цифру.";
            }

            if (!alphanumericRegex.test(password)) {
                return "Пароль может содержать только латинские или кириллические буквы и арабские цифры.";
            }

            if (spaceRegex.test(password)) {
                return "Пароль не может содержать пробелы.";
            }

            return null; // Пароль соответствует всем требованиям
        }

        function checkEmail(email) {
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (!emailRegex.test(email)) {
                return "Некорректный формат электронной почты.";
            }
            return null; // Электронная почта валидна
        }
    </script>
</@t.template>