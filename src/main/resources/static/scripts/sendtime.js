// function sendtime() {
//     let dateOfVisit = new Date(currentYear, currentMonth, global_CurrentDay);
//     let timeBlocks = global_BeginTime + '-' + global_EndTime;
//     let sendData = {
//         date: dateOfVisit,
//         timed_blocks: timeBlocks,
//         status: "sign",
//     }
//     console.log(sendData);
//     fetch('/shedule/'+compId, {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json',
//             'X-CSRF-Token': token
//         },
//         body: JSON.stringify(sendData)
//     })
// }
function sendtime() {
    let month = currentMonth+1; //js по другому повспринимает месяца от 0 до 11
    let dateOfVisit = global_CurrentDay + "-" + month + "-" + currentYear;
    let timeBlocks = global_BeginTime + '-' + global_EndTime;
    let dateField = document.getElementById('dateField');
    let timeBlocksField = document.getElementById('timeBlocksField');
    dateField.value = dateOfVisit;
    timeBlocksField.value = timeBlocks;
    console.log(dateField.value,timeBlocksField.value)
    document.getElementById('addVisit').submit();
}

function sendDataReport() {
    let loginField = document.getElementById('loginSend');
    let compIdField = document.getElementById('compIdSend');
    let dateFromField = document.getElementById('dateFromSend');
    let dateToField = document.getElementById('dateToSend');

    loginField.value = document.getElementById("loginInput").value;
    compIdField.value = document.getElementById("computerSelect").value;

    let dateFromUnformatted = new Date(document.getElementById("dateFromInput").value);
    let dateFromFormatted = dateFromUnformatted.toLocaleDateString('en-GB', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
    }).split('/').join('-');
    dateFromField.value = (dateFromFormatted == "Invalid Date") ? null : dateFromFormatted;

    let dateToUnformatted = new Date(document.getElementById("dateToInput").value);
    let dateToFormatted = dateToUnformatted.toLocaleDateString('en-GB', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
    }).split('/').join('-');
    dateToField.value = (dateToFormatted == "Invalid Date") ? null : dateToFormatted;
    console.log(loginField.value,compIdField.value,dateFromField.value,dateToField.value)
    document.getElementById('getReport').submit();
}