<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>

    <%--modal window bootStrap example--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="style/style.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script src="/javaScript/script.js"></script>
    <title>Main Page</title>
</head>

<body>
<div class="container">
    <h1>Life's good</h1>
    <table id="mainTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>Caller</th>
            <th>Event</th>
            <th>Receiver</th>
            <th>Timestamp</th>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <th id="callSearch">Caller</th>
            <th>Event</th>
            <th id="receiverSearch">Receiver</th>
            <th>Timestamp</th>
        </tr>
        </tfoot>
    </table>

    <button type="button" class="btn btn-info btn-lg" data-toggle="modal"
            onclick="window.location.href='<s:url action="save"/>'">Save to CSV
    </button>


    <!-- Modal Call Details Dialog-->
    <div class="modal fade" id="callDialog" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Call: <nobr id="callIdHeader"></nobr></h4>
                </div>
                <div class="modal-body">
                    <table id="tableCallDialog" class="display" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th>Caller</th>
                            <th>Event Name</th>
                            <th>Receiver</th>
                            <th>Timestamp</th>
                            <th>Call Id</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>

    <!-- Modal All Calls Dialog-->
    <div class="modal fade" id="allCalls" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">All Calls: <nobr id="callerHeader"></nobr></h4>
                </div>
                <div class="modal-body">
                    <table id="tableAllCalls" class="display" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th>Timestamp</th>
                            <th>Call Duration</th>
                            <th>Receiver</th>
                            <th>Type</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>