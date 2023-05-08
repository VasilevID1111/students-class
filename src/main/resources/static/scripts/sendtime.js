function sendtime() {
    let dateOfVisit = new Date(currentYear, currentMonth, global_CurrentDay);
    let timeBlocks = global_BeginTime + '-' + global_EndTime;
    let sendData = {
        date: dateOfVisit,
        timed_blocks: timeBlocks,
        status: "sign",
    }
    console.log(sendData);
    fetch('/shedule/'+compId, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-Token': token
        },
        body: JSON.stringify(sendData)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}