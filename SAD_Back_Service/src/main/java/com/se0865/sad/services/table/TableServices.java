package com.se0865.sad.services.table;


import com.se0865.sad.entities.SadTable;
import com.se0865.sad.logic.TableManager;
import com.se0865.sad.response.SadResponse;
import com.se0865.sad.utils.CommonUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 19/07/2015.
 */
@Path("/service/table")
public class TableServices {

    @Inject
    private TableManager tableManager;

    @Inject
    private CommonUtil commonUtil;

    @GET
    @Path("list_table")
    @Transactional
    @Produces("application/json")
    public Response getListOrder(@QueryParam("last_update") long lastUpdate,
                                 @HeaderParam("session") String session) {
        SadResponse response = new SadResponse();
        List<SadTable> tableList;
        if (lastUpdate != 1 && lastUpdate != 0) {
            tableList = tableManager.getLastedUpdateTable(Long.valueOf(lastUpdate));
        } else {
            tableList = tableManager.getTableList();
        }
        response.setData(tableList);
        return response.ok();
    }

}
