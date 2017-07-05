$(document).ready(function () {

    // Setup - add a text input to footer cells
    $('#callSearch, #receiverSearch').each(function () {
        var title = $(this).text();
        $(this).html('<input type="text" placeholder="Search ' + title + '" />');
    });

    // DataTable
    var table = $('#mainTable').DataTable({
        "dom": '<"top"l>rt<"bottom"ip><"clear">',
        "processing": true,
        "serverSide": true,
        "sAjaxSource": "ShowAllAction",
        "lengthMenu": [5, 10, 25, 50, 100],
        "columnDefs": [{
            "targets": [1, 3],
            "orderable": false
        }],
        "pagingType": "full_numbers",
        "columns": [
            {"data": "caller"},
            {"data": "eventName"},
            {"data": "receiver"},
            {"data": "recordDate"}
        ],
        initComplete: function () {
            this.api().columns(1).every(function () {
                var column = this;
                var select = $('<select><option value=""></option></select>')
                    .appendTo($(column.footer()).empty())
                    .on('change', function () {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );

                        column
                            .search(val ? '^' + val + '$' : '', true, false)
                            .draw();
                    });

                column.data().unique().sort().each(function (d, j) {
                    select.append('<option value="' + d + '">' + d + '</option>')
                });

            });
        }
    });

    // Apply the search
    table.columns().every(function () {
        var that = this;

        $('input', this.footer()).on('keyup change', function () {
            if (that.search() !== this.value) {
                that
                    .search(this.value)
                    .draw();
            }
        });
    });



    $('#mainTable tbody').on('click', 'td', function () {

        var rowIndex = table.cell(this).index().row;
        var key = table.cell(rowIndex, 3).data();
        var colIndex = $(this).index();
        if (colIndex == 0) {

            // Modal - All calls by caller
            var caller = table.cell(this).data();
            $.post("showEventAction", {caller: caller}, function (data) {

                var newRes = JSON.parse(data);
                var result = Object.keys(newRes).map(function (_) {
                    return newRes[_];
                });

                $('#allCalls').modal('show');
                document.getElementById("callerHeader").innerHTML = caller;
                $('#tableAllCalls').DataTable({
                    searching: false,
                    ordering: false,
                    destroy: true,
                    paging: false,
                    scrollY: 400,
                    data: result,
                    columns: [
                        {data: 'recordDate'},
                        {data: 'talkDuration'},
                        {data: 'receiver'},
                        {data: 'callType'}
                    ]
                });

            });

        } else {

            // Modal - Call details
            $.post("showCallIdAction", {key: key}, function (data) {
                // Extracting header "Call Type"
                var callType = data.substring(data.indexOf("callType=") + 9);
                var data = data.replace("callType=" + callType, "");

                var newRes = JSON.parse(data);
                var result = Object.keys(newRes).map(function (_) {
                    return newRes[_];
                });

                $('#callDialog').modal('show');
                document.getElementById("callIdHeader").innerHTML = callType;
                $('#tableCallDialog').DataTable({
                    searching: false,
                    ordering: false,
                    destroy: true,
                    paging: false,
                    scrollY: 400,
                    data: result,
                    columns: [
                        {data: 'caller'},
                        {data: 'eventName'},
                        {data: 'receiver'},
                        {data: 'recordDate'},
                        {data: 'callId'}
                    ]
                });
            });
        }

    });




    /*$('#mainTable tbody').on('click', 'tr', function () {
        var key = $('td', this).eq(3).text();
    } );*/
});






