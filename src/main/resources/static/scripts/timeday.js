let global_i = -1;
let global_BeginTime = -1;
let global_EndTime = 36;

function clickHandlerTime(e) {
    var cellId = parseInt(e.target.id.replace("time_cell", ''));
    let timeRangeElement = document.getElementById('time-range');
    if (global_i == cellId){
        e.target.style.backgroundColor = '';
        global_i = -1;
        global_BeginTime = -1;
        global_EndTime  = 36;
        timeRangeElement.textContent = "";
        return;
    }
    if (global_i == -1) {
        for (let i = 0; i <= 35; i++) {
            let cell = document.getElementById("time_cell"+i);
            cell.style.backgroundColor = '';
        }
        e.target.style.backgroundColor = 'lightgreen';
        global_i = cellId;
        global_BeginTime = cellId;
        global_EndTime = cellId;
    } else {
        let min; let max; let counter=0;
        if (global_i > cellId) {
            min = cellId;
            max = global_i;
            global_BeginTime = cellId;
        } else {
            min = global_i;
            max = cellId;
            global_EndTime = cellId;
        }
        for (min; min <= max; min++) {
            counter++;
            let cell = document.getElementById("time_cell"+min);
            cell.style.backgroundColor = 'lightgreen';
            if (counter > 16) {
                for (let i = min; i > min-16; i--) {
                    let cell = document.getElementById("time_cell"+i);
                    cell.style.backgroundColor = '';
                }
                e.target.style.backgroundColor = '';
                global_BeginTime = global_i;
                global_EndTime   = global_i;
                alert('Нельзя больше 4 часов арендовывать комп');
                return;
            }
        }
        global_i = -1;
    }
    timeRangeElement.textContent = convertTimeRange(global_BeginTime + "-" + global_EndTime);
}
function showTimeTable(selectedDate) {
    let daysOff =[];
    let dateFormatted = selectedDate.toLocaleDateString('en-GB', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
    }).split('/').join('-');
    console.log(dateFormatted);
    if (dateFormatted in shedules) {
        daysOff = shedules[dateFormatted] + ",";
    }
    console.log("daysOff",daysOff);


    //creation of table with time
    let tbl = document.getElementById("timeday-body"); // body of the timeday
    // clearing all previous cells
    tbl.innerHTML = "";
    // creating all cells
    for (let i = 0; i < 9; i++) {
        // creates a table row
        let row = document.createElement("tr");

        //creating individual cells, filing them up with data.
        for (let j = 0; j < 4; j++) {
            let cell = document.createElement("td");
            let cellText;
            let hour = i+9;
            /* let cellId = document.createNode; */
            switch (j) {
                case 0:
                    cellText = document.createTextNode(hour+":00");
                    break;
                case 1:
                    cellText = document.createTextNode(hour+":15");
                    break;
                case 2:
                    cellText = document.createTextNode(hour+":30");
                    break;
                case 3:
                    cellText = document.createTextNode(hour+":45");
                    break;
            }
            let num = i*4+j;


            cell.appendChild(cellText);
            if (daysOff.includes(","+num+",")) {
                cell.classList.add("nowork");
            } else {
                cell.addEventListener('click',clickHandlerTime);
            }
            cell.setAttribute('id','time_cell'+num)
            row.appendChild(cell);
        }
        tbl.appendChild(row);  // appending each row into timeday body.
    }
}

function convertTimeRange(timeRange) {
    const startHour = 9;
    const startMinute = 0;
    const timeSlotMinutes = 15;

    const [start, end] = timeRange.split('-').map(Number);
    const startTime = startHour * 60 + startMinute + start * timeSlotMinutes;
    const endTime = startHour * 60 + startMinute + end * timeSlotMinutes + 15;
    console.log(startTime,endTime);
    const startHourStr = String(Math.floor(startTime / 60)).padStart(2, '0');
    const startMinuteStr = String(startTime % 60).padStart(2, '0');
    const endHourStr = String(Math.floor(endTime / 60)).padStart(2, '0');
    const endMinuteStr = String(endTime % 60).padStart(2, '0');

    return `c ${startHourStr}-${startMinuteStr} до ${endHourStr}-${endMinuteStr}`;
}