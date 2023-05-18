<#import "blocks/template.ftlh" as t>
<@t.template>
    <div class="container">
        <#if globalRole = "ROLE_WORKER">
            <div class="form">
                <div class="col-md-6">
                    <h3>Создать компьютер</h3>
                </div>
                <form action="/computers/create" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <div class="form-group">
                        <label for="name">Название компьютера:</label>
                        <input type="text" class="form-control" id="name" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="purpose">Описание компьютера:</label>
                        <input type="text" class="form-control" id="purpose" name="purpose" required>
                    </div>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="workable" name="workable">
                        <label class="form-check-label" for="workable">Компьютер рабочий</label>
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Добавить компьютер</button>
                </form>
            </div>
        </#if>
        <div class="row">
            <div class="col-md-6">
                <h3>Выберите компьютер</h3>
            </div>
        </div>
        <table class="table table-bordered">
            <thead class="table-dark">
            <tr>
                <th>Название компьютера</th>
                <th>Описание компьютера</th>
                <th>Записаться</th>
                <#if globalRole = "ROLE_WORKER">
                    <th>Рабочий</th>
                    <th>Удалить</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <#list computers as computer>
                <#if computer.workable = false && globalRole = "ROLE_STUDENT">
                    <#continue>
                </#if>
                <tr>
                    <td>${computer.name}</td>
                    <td>${computer.purpose}</td>
                    <td class="text-center">
                        <a href="/computers/${computer.compId}" class="btn btn-primary">Записаться</a>
                    </td>
                    <#if globalRole = "ROLE_WORKER">
                        <td>
                            <input type="checkbox" name="isWorkable" value="${computer.workable?c}"
                                    ${computer.workable?string('checked', '')} disabled>
                        </td>
                        <td class="text-center">
                            <a href="/computers/delete/${computer.compId}" class="btn btn-danger">Удалить</a>
                        </td>
                    </#if>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    </div>
</@t.template>