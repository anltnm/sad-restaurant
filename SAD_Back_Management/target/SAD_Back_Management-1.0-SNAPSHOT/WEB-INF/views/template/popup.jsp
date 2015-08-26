<%--
  Created by IntelliJ IDEA.
  User: AnLTNM-SE60906
  Date: 01/08/2015
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="modalbox" style="display: none;">
    <div class="devoops-modal">
        <div class="devoops-modal-header">
            <div class="modal-header-name">
                <span>Information</span>
            </div>
            <div class="box-icons">
                <a class="close-link">
                    <i class="fa fa-times"></i>
                </a>
            </div>
        </div>
        <div class="devoops-modal-inner">
            <form id="event_form">
                <div class="form-group has-success has-feedback" id="popupMessage"
                     style="text-align: center;font-size: large;">
                </div>
            </form>
        </div>
        <div class="devoops-modal-bottom" style="text-align: center">
            <div id="confirm-popup">
                <button id="event_cancel" type="cancel" class="btn btn-default" style="margin-right: 30px;"
                        onclick="closePopup()">
                    Cancel
                </button>
                <button id="event_ok" type="button" class="btn btn-primary" onclick="confirmed()">OK
                </button>
            </div>
            <div id="info-popup" class="close">
                <button id="event_ok_info_popup" type="cancel" class="btn btn-default" style="margin-right: 30px;"
                        onclick="closePopup()">
                    OK
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    callbackFunction = function () {
        console.log("call at popup");
    };
    function closePopup() {
        $("#modalbox").removeClass("display");
    }
    function openPopup(isConfirmPopup) {
        $("#modalbox").addClass("display");
        if (isConfirmPopup === true) {
            $("#confirm-popup").add("display");
            $("#info-popup").removeClass("display");
        } else {
            $("#confirm-popup").removeClass("display");
            $("#info-popup").addClass("display");
        }
    }
    function confirmed() {
        callbackFunction();
        closePopup();
    }
</script>