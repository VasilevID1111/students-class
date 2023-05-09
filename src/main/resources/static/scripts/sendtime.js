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
    alert(dateOfVisit);
    let timeBlocks = global_BeginTime + '-' + global_EndTime;
    let dateField = document.getElementById('dateField');
    let timeBlocksField = document.getElementById('timeBlocksField');
    dateField.value = dateOfVisit;
    timeBlocksField.value = timeBlocks;
    console.log(dateField.value,timeBlocksField.value)
    document.getElementById('addVisit').submit();
}