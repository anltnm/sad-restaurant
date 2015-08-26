<%--
  Created by IntelliJ IDEA.
  User: AnLTNM-SE60906
  Date: 27/05/2015
  Time: 8:27 PM
  To change this template use File | Settings | File Templates.
--%>
<ul class="nav main-menu">
    <li>
        <a href="../dashboard/dashBoard" class="ajax-link">
            <i class="fa fa-dashboard"></i>
            <span class="hidden-xs">Dashboard</span>
        </a>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <span class="hidden-xs">Category Manager</span>
        </a>
        <ul class="dropdown-menu">
            <li><a class="ajax-link" href="../category/listCategory">Category List</a></li>
            <li><a class="ajax-link" href="../category/addCategoryView">Add New Category</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <span class="hidden-xs">Food Manager</span>
        </a>
        <ul class="dropdown-menu">
            <li><a class="ajax-link" href="../food/listFood">Food List</a></li>
            <li><a class="ajax-link" href="../food/addFodView">Add New Food</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <span class="hidden-xs">Extra Group Manager</span>
        </a>
        <ul class="dropdown-menu">
            <li><a class="ajax-link" href="../extraFood/listExtraGroup">Extra Group List</a></li>
            <li><a class="ajax-link" href="../extraFood/addExtraFoodGroupView">Add New Group</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <span class="hidden-xs">Extra Food Manager</span>
        </a>
        <ul class="dropdown-menu">
            <li><a class="ajax-link" href="../extraFood/listExtraFood">Extra Food List</a></li>
            <li><a class="ajax-link" href="../extraFood/addExtraFoodView">Add New Extra Food</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <span class="hidden-xs">User Manager</span>
        </a>
        <ul class="dropdown-menu">
            <li><a class="ajax-link" href="../user/userListView">User List</a></li>
            <li><a class="ajax-link" href="../user/addUserView">Add New Staff</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <span class="hidden-xs">Order</span>
        </a>
        <ul class="dropdown-menu">
            <li><a class="ajax-link" href="./listOrder">List order</a></li>
        </ul>
    </li>
</ul>
