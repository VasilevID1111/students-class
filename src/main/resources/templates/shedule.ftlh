<#import "blocks/template.ftlh" as t>
<@t.template>
    <div class="container mt-5">
        <div class="row">
            <div class="col-sm-12 col-md-6 col-lg-6">
                <div class="card border-0">
                    <h3 class="card-header text-center" id="monthAndYear"></h3>
                    <div class="card-group d-flex">
                        <button class="btn btn-outline-primary btn-block flex-grow-1" id="previous" onclick="previous()">Назад</button>
                        <button class="btn btn-outline-primary btn-block flex-grow-1" id="next" onclick="next()">Вперед</button>
                    </div>
                    <table class="table table-bordered border-dark" id="calendar">
                        <thead>
                        <tr>
                            <th>Пн</th>
                            <th>Вт</th>
                            <th>Ср</th>
                            <th>Чт</th>
                            <th>Пт</th>
                            <th>Сб</th>
                            <th>Вс</th>
                        </tr>
                        </thead>
                        <tbody id="calendar-body" class="border border-dark">
                        </tbody>
                    </table>
                </div>

            </div>
            <div class="col-sm-12 col-md-6 col-lg-6">
                <div class="card border-0">
                    <table class="table table-bordered border-dark" id="timeday">
                        <thead>
                        <tr>
                            <th colspan="4" class="text-center">Время</th>
                        </tr>
                        </thead>
                        <tbody id="timeday-body" class="border border-dark">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card border-0">
                <div class="card-header text-center">
                    <h5>Время записи</h5>
                    <span id="time-range"></span>
                </div>
            </div>
            <div class="form-group">
                <form id="addVisit" action="/shedule/${compId}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="hidden" name="date" id="dateField">
                    <input type="hidden" name="timed_blocks" id="timeBlocksField">
                    <input type="hidden" name="status" value="sign">
                    <button id="sendButton" class="btn btn-primary btn-block" style="width: 100%;" onclick="sendtime()">
                        Записаться
                    </button>
                </form>
            </div>
        </div>

    </div>
    <script>
        let shedules = {
            <#list shedules?keys as key>
            "${key}": "${shedules[key]}",
            </#list>
        };
        console.log(shedules)
        let today = new Date();
        let currentMonth = today.getMonth();
        let currentYear = today.getFullYear();

        let months = ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"];

        let monthAndYear = document.getElementById("monthAndYear");
        showCalendar(currentMonth, currentYear);
        showTimeTable(today);
    </script>
</@t.template>
