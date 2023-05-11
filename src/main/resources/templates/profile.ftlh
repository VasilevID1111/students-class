<#import "blocks/template.ftlh" as t>
<@t.template>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-6 col-md-8 col-sm-10">
                <div class="card my-5">
                    <div class="card-body">
                        <h3 class="card-title text-center mb-4">Профиль пользователя</h3>
                        <form action="#" method="post">
                            <div class="form-group">
                            <h5 class="mb-3">Электронная почта</h5>
                                <input type="email" class="form-control" id="email" name="email"
                                       value="example@mail.com" required>
                            </div>
                            <hr>
                            <h5 class="mb-3">Изменение пароля</h5>
                            <div class="form-group">
                                <label for="currentPassword">Текущий пароль</label>
                                <input type="password" class="form-control" id="currentPassword" name="currentPassword"
                                       required>
                            </div>
                            <div class="form-group">
                                <label for="newPassword">Новый пароль</label>
                                <input type="password" class="form-control" id="newPassword" name="newPassword"
                                       minlength="8" required>
                            </div>
                            <div class="form-group">
                                <label for="confirmNewPassword">Подтвердите новый пароль</label>
                                <input type="password" class="form-control" id="confirmNewPassword"
                                       name="confirmNewPassword" minlength="8" required>
                            </div>
                            <div class="form-group text-center mt-5">
                                <button type="submit" class="btn btn-primary">Сохранить изменения</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@t.template>