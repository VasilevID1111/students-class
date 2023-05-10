<#import "blocks/template.ftlh" as t>
<@t.template>
    <#if errorMessage?has_content>
        <div class="alert alert-danger" role="alert">
            ${errorMessage}
        </div>
    </#if>

    <table class="table table-striped">
        <tr>
            <th>Логин</th>
            <th>ФИО</th>
            <th>Электронная почта</th>
            <th>Активность</th>
            <th>Дата регистрации</th>
            <th>Деактивировать пользователя</th>
            <th>Удалить пользователя</th>
        </tr>
        <#list users as user>
            <tr>
                <th>${user.login}</th>
                <th>${user.fio}</th>
                <th>${user.email}</th>
                <th><input type="checkbox" name="isActive"
                           value="${user.active?c}" ${user.active?string('checked', '')} disabled/></th>
                <th>${user.dateOfCreated?date?string('dd-MM-yyyy')}</th>
                <th><a href="/users/deactivate/${user.id}">Деактивировать</a></th>
                <th><a href="/users/delete/${user.id}">Удалить</a></th>
            </tr>
        </#list>
        <form action="/users/create" method="post">
            <tr>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <th><input type="text" name="login" id="login"/></th>
                <th><input type="text" name="fio" id="fio"/></th>
                <th><input type="text" name="email" id="email"/></th>
                <th><input type="checkbox" name="isActive" checked/></th>
                <th><span name="date" id="isActiveCreate"></span></th>
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
        document.getElementById('isActiveCreate').textContent = formattedDate;
    </script>
</@t.template>