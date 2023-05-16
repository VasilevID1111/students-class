<#import "blocks/template.ftlh" as t>
<@t.template>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h1>Компьютеры</h1>
            </div>
        </div>
        <div class="row">
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
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Название компьютера</th>
                    <th>Описание компьютера</th>
                    <th>Рабочий</th>
                    <th>Записаться</th>
                    <th>Удалить</th>
                </tr>
                </thead>
                <tbody>
                <#list computers as computer>
                    <tr>
                        <td>${computer.name}</td>
                        <td>${computer.purpose}</td>
                        <td>
                            <input type="checkbox" name="isWorkable" value="${computer.workable?c}"
                                    ${computer.workable?string('checked', '')} disabled>
                        </td>
                        <td>
                            <a href="/computers/${computer.compId}" class="btn btn-primary">Записаться</a>
                        </td>
                        <td>
                            <a href="/computers/delete/${computer.compId}" class="btn btn-danger">Удалить</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@t.template>