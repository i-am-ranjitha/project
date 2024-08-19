$(document).ready(function() {
    // Add user functionality
    $("#adduser").click(function() {
        var userName = $("#username").val();
        var userRole = $("#userrole").val();
alert(userName);
        $.ajax({
            url: "http://162.214.73.83:9080/JAVA_TRAINING/api/create/user",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                Name: userName,
                Role: userRole
            }),
            success: function(response) {
                console.log(response)
                alert(response.result);
                $("#username").val('');
                $("#userrole").val('');
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('Error adding user:', textStatus, errorThrown);
                alert('Error adding user. Please try again.');
            }
        });
    });
});
