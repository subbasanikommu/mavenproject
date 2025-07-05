
document.addEventListener("DOMContentLoaded", (function(){
    $(".date").each(function() {
        try {
            var frequency = $(this).data("frequency");
            var date = new Date($(this).data("date"));
            var today = new Date();
            var newDate = new Date(date);
            var lastDayOfMonth = new Date(newDate.getFullYear(), newDate.getMonth()+1, 0);
            if (frequency == "weekly") {
                date.setDate(date.getDate() + Math.trunc((today - date)/(1000 * 3600 * 24*7))*7);
                $(this).text("Publish Date: " +date.getDate() + " " + date.toLocaleString('default', { month: 'long' }) + " " + date.getFullYear());
                console.log((today - date)/(1000 * 3600 * 24))
            } else if (frequency == "monthly") {
                if (newDate.valueOf() === lastDayOfMonth.valueOf()) {
                    var newDate = new Date(today.getFullYear(), today.getMonth(), 0);
                    console.log(newDate);
                } else if(today.getMonth() == 2 && date.getDate()>=29 && today.getDate()<29){
                    var newDate = new Date(today.getFullYear(), today.getMonth(), 0);
                    console.log(newDate);
                }else if (today > date.setMonth(today.getMonth())) {
                    var newDate = new Date(date);
                    console.log(newDate);
                } else{
                    newDate.setMonth(today.getMonth()-1);
                    console.log(newDate);
                }
                $(this).text("Publish Date: " +newDate.getDate() + " " + newDate.toLocaleString('default', { month: 'long' }) + " " + newDate.getFullYear());
            } else {
                $(this).text("Publish Date: " +today.getDate() + " " + today.toLocaleString('default', { month: 'long' }) + " " + today.getFullYear());
            }
        } catch (error) {
            console.log(error);
        }
    });
  }))