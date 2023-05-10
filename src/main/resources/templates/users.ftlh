<#import "blocks/template.ftlh" as t>
<@t.template>
    <#list users as user>
        <div>
            <p>
                <b>${user.login}</b>
                | ${user.fio}
                | ${user.roles.}
                | <a href="/visits/delete/${visit.visit_id}">Отменить запись</a>
            </p>
        </div>
    </#list>
</@t.template>