<#import "blocks/template.ftlh" as t>
<@t.template>
    <form action="/login" method="post">
        <!-- Login input -->
        <div class="form-outline mb-4">

            <input type="text" id="username" name="username" class="form-control"/>
            <label class="form-label" for="username">Логин</label>
        </div>

        <!-- Password input -->
        <div class="form-outline mb-4">
            <input type="password" id="password" name="password" class="form-control"/>
            <label class="form-label" for="password">Пароль</label>
        </div>

        <!-- 2 column grid layout for inline styling -->
        <div class="row mb-4">
            <div class="col d-flex justify-content-center">
                <!-- Checkbox -->
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="form2Example31" checked/>
                    <label class="form-check-label" for="form2Example31"> Запомнить пароль </label>
                </div>
            </div>

            <div class="col">
                <!-- Simple link -->
                <a href="#!">Забыли пароль?</a>
            </div>
        </div>

        <!-- Submit button -->
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary btn-block mb-4">Войти</button>

    </form>
</@t.template>