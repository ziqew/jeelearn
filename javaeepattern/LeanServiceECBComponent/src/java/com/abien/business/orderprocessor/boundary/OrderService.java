/**
This file is part of javaee-patterns.

javaee-patterns is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

javaee-patterns is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.opensource.org/licenses/gpl-2.0.php>.

* Copyright (c) 30. June 2009 Adam Bien, blog.adam-bien.com
* http://press.adam-bien.com
*/
package com.abien.business.orderprocessor.boundary;
import com.abien.business.audits.CallAudit;
import com.abien.business.orderprocessor.entity.Order;
import javax.ejb.Stateless;
import javax.ejb.EJB;
import com.abien.business.orderprocessor.control.BillingService;
import com.abien.business.orderprocessor.control.DeliveryService;
import com.abien.business.orderprocessor.control.Warehouse;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * @author Adam Bien (blog.adam-bien.com)
 */
@Path("/orders/")
@Interceptors(CallAudit.class)
@Stateless public class OrderService {

    @EJB BillingService billing;
    @EJB DeliveryService delivery;
    @EJB Warehouse warehouse;

    @PUT
    @Produces({"application/xml","application/json"})
    @Consumes({"application/xml","application/json"})
    public Order order(Order newOrder){
        Order order = warehouse.checkout(newOrder);
        billing.payForOrder(order);
        delivery.deliver(order);
        return order;
    }

    @GET
    @Path("{orderid}/")
    @Produces({"application/xml","application/json"})
    public Order status(@PathParam("orderid") long orderId){
       return delivery.status(orderId);
    }
}
