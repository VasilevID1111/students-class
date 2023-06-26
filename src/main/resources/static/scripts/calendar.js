let global_CurrentDay = -1;

function clickHandlerDay(e) {
    var cell = e.target;
    let previous_cell = $('td').filter(function () {
        return $(this).text().trim().includes(global_CurrentDay);
    })[0];
    console.log(previous_cell);
    previous_cell.classList.remove('bg-info');
    previous_cell.classList.remove('hover')
    previous_cell.classList.add("free");
    global_CurrentDay = cell.innerText;
    cell.classList.add("bg-info");
    let currentDate = new Date(currentYear, currentMonth, global_CurrentDay);
    showTimeTable(currentDate);
}

function next() {
    currentYear = (currentMonth === 11) ? currentYear + 1 : currentYear;
    currentMonth = (currentMonth + 1) % 12;
    showCalendar(currentMonth, currentYear);
    let currentDate = new Date(currentYear, currentMonth, global_CurrentDay);
    showTimeTable(currentDate);
}

function previous() {
    currentYear = (currentMonth === 0) ? currentYear - 1 : currentYear;
    currentMonth = (currentMonth === 0) ? 11 : currentMonth - 1;
    showCalendar(currentMonth, currentYear);
    let currentDate = new Date(currentYear, currentMonth, global_CurrentDay);
    showTimeTable(currentDate);
}

function showCalendar(month, year) {
    let firstFree = true;
    let daysOff = [];
    for (const key in shedules) {
        if (shedules[key] === "out") {
            let date = key.split("-");
            console.log("date",date);
            console.log(currentMonth,currentYear);
            if (parseInt(date[1], 10)-1 == currentMonth && parseInt(date[2],10) == currentYear) {
                daysOff.push(parseInt(date[0],10));
            }
        }
    }
    console.log("daysOffCalendar",daysOff);
    let firstDay = (new Date(year, month)).getDay() - 1;
    let daysInMonth = 32 - new Date(year, month, 32).getDate();

    let tbl = document.getElementById("calendar-body"); // body of the calendar

    // clearing all previous cells
    tbl.innerHTML = "";

    // filing data about month and in the page via DOM.
    monthAndYear.innerHTML = months[month] + " " + year;

    // creating all cells
    let date = 1;
    for (let i = 0; i < 6; i++) {
        // creates a table row
        let row = document.createElement("tr");

        //creating individual cells, filing them up with data.
        for (let j = 0; j < 7; j++) {
            if (date > daysInMonth) { //для последних дней
                break;
            }
            let cell, cellText;
            cell = document.createElement("td");
            if (i === 0 && j < firstDay) { //пустые дни
                cellText = document.createTextNode("");
            } else {
                cellText = document.createTextNode(date);
                if ((date < today.getDate() && year === today.getFullYear() && month === today.getMonth()) ||
                    (year <= today.getFullYear() && month < today.getMonth()) ||
                    (year < today.getFullYear()) ||
                    (j === 5 || j === 6 || daysOff.includes(date))) {
                    cell.classList.add("nowork");
                } else {
                    cell.classList.add("free");
                    cell.addEventListener('click', clickHandlerDay);
                    cell.classList.add("hover");
                    if (firstFree) { // color free first's date
                        global_CurrentDay = cellText.wholeText;
                        cell.classList.add("bg-info");
                        firstFree = false;
                    }
                }
                date++;
            }
            cell.appendChild(cellText);
            row.appendChild(cell);
        }
        tbl.appendChild(row);  // appending each row into calendar body.
    }
}