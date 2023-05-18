<#import "blocks/template.ftlh" as t>
<@t.template>
    <#if message?has_content>
        <div class="alert alert-danger" role="alert">
            ${message}
        </div>
    </#if>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th>Логин</th>
            <th>ФИО</th>
            <th>Электронная почта</th>
            <th>Активность</th>
            <th>Дата регистрации</th>
            <th>Изменить активность</th>
            <th>Удалить пользователя</th>
        </tr>
        </thead>
        <#list users as user>
            <tr>
                <th>${user.login}</th>
                <th>${user.fio}</th>
                <th>${user.email}</th>
                <th><input type="checkbox" name="active"
                           value="${user.active?c}" ${user.active?string('checked', '')} disabled/></th>
                <th>${user.dateOfCreated?date?string('dd-MM-yyyy')}</th>
                <th><a href="/users/deactivate/${user.id}">Переключить</a></th>
                <th><a href="/users/delete/${user.id}">Удалить</a></th>
            </tr>
        </#list>
        <form action="/users/create" method="post">
            <tr>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <th><input type="text" name="login" id="login"/></th>
                <th><input type="text" name="fio" id="fio"/></th>
                <th><input type="email" name="email" id="email"/></th>
                <th><input type="checkbox" id="isActive" name="isActive" checked="true"/></th>
                <th><span name="date" id="dateCreate"></span></th>
                <th>
                    <button type="submit">Создать</button>
                </th>
                <th></th>
            </tr>
        </form>
    </table>
    <script>
        const today = new Date();
        const day = String(today.getDate()).padStart(2, '0'); // Add leading zero if day is single digit
        const month = String(today.getMonth() + 1).padStart(2, '0'); // Add leading zero if month is single digit
        const year = today.getFullYear();
        const formattedDate = day + "-" + month + "-" + year;
        console.log(formattedDate);
        document.getElementById('dateCreate').textContent = formattedDate;
    </script>
</@t.template>