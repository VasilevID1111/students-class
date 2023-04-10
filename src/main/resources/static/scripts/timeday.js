let global_i = -1;
function clickHandlerTime(e) {
    var cellId = parseInt(e.target.id.replace("time_cell", ''));
    if (global_i == cellId){
        e.target.style.backgroundColor = '';
        global_i = -1;
        return;
    }
    if (global_i == -1) {
        for (let i = 0; i <= 35; i++) {
            let cell = document.getElementById("time_cell"+i);
            cell.style.backgroundColor = '';
        }
        e.target.style.backgroundColor = 'lightgreen';
        global_i = cellId;
    } else {
        let min; let max; let counter=0;
        if (global_i > cellId) {
            min = cellId;
            max = global_i;
        } else {
            min = global_i;
            max = cellId;
        }
        for (min; min <= max; min++) {
            counter++;
            let cell = document.getElementById("time_cell"+min);
            cell.style.backgroundColor = 'lightgreen';
            if (counter > 16) {
                for (let i = min; i > min-16; i--) {
                    console.log(i,min,max);
                    let cell = document.getElementById("time_cell"+i);
                    cell.style.backgroundColor = '';
                }
                e.target.style.backgroundColor = '';
                alert('Нельзя больше 4 часов арендовывать комп');
                return;
            }
        }
        global_i = -1;
    }
}

function showTimeTable() {
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
            cell.appendChild(cellText);
            cell.addEventListener('click',clickHandlerTime);
            let num = i*4+j;
            cell.setAttribute('id','time_cell'+num)
            row.appendChild(cell);
        }
        tbl.appendChild(row);  // appending each row into timeday body.
    }
}