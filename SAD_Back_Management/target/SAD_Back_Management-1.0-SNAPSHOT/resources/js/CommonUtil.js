/**
 * Created by AnLTNM-SE60906 on 29/07/2015.
 */
$.formatDateTime.defaults['utc'] = [true];
function parseLongToString(dateString) {
    var timeLong = $.formatDateTime("mm/dd/y", parseInt(dateString, 10));
    return timeLong;
}