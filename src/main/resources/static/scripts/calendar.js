let global_CurrentDay = -1;
function clickHandlerDay(e) {
    var cell = e.target;
    let previous_cell = $('td').filter(function() {
        return $(this).text().trim().includes(global_CurrentDay);
    })[0];
    console.log(previous_cell);
    previous_cell.classList.remove('bg-info');
    previous_cell.classList.remove('hover')
    previous_cell.classList.add("free");
    global_CurrentDay = cell.innerText;
    cell.classList.add("bg-info");
    let currentDate = new Date(currentYear,currentMonth,global_CurrentDay);
    showTimeTable(currentDate);
}

function next() {
    currentYear = (currentMonth === 11) ? currentYear + 1 : currentYear;
    currentMonth = (currentMonth + 1) % 12;
    showCalendar(currentMonth, currentYear);
}

function previous() {
    currentYear = (currentMonth === 0) ? currentYear - 1 : currentYear;
    currentMonth = (currentMonth === 0) ? 11 : currentMonth - 1;
    showCalendar(currentMonth, currentYear);
}

function showCalendar(month, year) {

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
            if (i === 0 && j < firstDay) {
                let cell = document.createElement("td");
                let cellText = document.createTextNode("");
                cell.appendChild(cellText);
                row.appendChild(cell);
            } else if (date > daysInMonth) {
                break;
            } else {
                let cell = document.createElement("td");
                let cellText = document.createTextNode(date);
                /* let cellId = document.createNode; */
                if (date < today.getDate() && year === today.getFullYear() && month === today.getMonth() ||
                    (year <= today.getFullYear() && month < today.getMonth()) ||
                    (year < today.getFullYear())) {
                    cell.classList.add("nowork");
                } else {
                    if (j === 5 || j === 6 ) {
                        cell.classList.add("nowork");
                    } else {
                        cell.classList.add("free");
                        cell.addEventListener('click', clickHandlerDay);
                        cell.classList.add("hover");
                    }
                    if (date === today.getDate() && year === today.getFullYear() && month === today.getMonth()) {
                        global_CurrentDay = cellText.wholeText;
                        cell.classList.add("bg-info");
                    } // color today's date
                }
                cell.appendChild(cellText);
                row.appendChild(cell);
                date++;
            }


        }
        tbl.appendChild(row);  // appending each row into calendar body.
    }

}