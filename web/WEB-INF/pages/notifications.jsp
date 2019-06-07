<%-- 
    Document   : notification
    Created on : 6 déc. 2018, 13:07:07
    Author     : Administrateur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:import url="/WEB-INF/composants/breadcrum/breadcrum.jsp"/>

<div class="row" ng-app="ccmanager.notification">

    <div class="col-md-12" ng-controller="NotificationController">
        <div class="content">
            <div class="content-wrapper">
                <div class="content-header row">
                </div>
                <div class="content-body">
                    <div class="card email-app-details d-none d-lg-block">
                        <div class="card-content">
                            <div class="email-app-options card-body">
                               
                            </div>
                            <div class="email-app-title card-body">
                                <h3 class="list-group-item-heading">Notifications</h3>
                                <p class="list-group-item-text">
                                    <span class="primary">
                                        <input type="checkbox" ng-checked="checkbox"  value="0" ng-model="checkbox" /> <span style="cursor: pointer" class="badge badge-primary" ng-click="setAllNotification()" title="marquer comme lus les notifications sélectionnées" data-toggle="tooltip">Marquer comme lu</span> <i class="float-right font-medium-3 ft-star warning"></i></span>
                                </p>
                            </div>
                            <div class="email-app-list-wraper col-md-12 card p-0">
                                <div class="email-app-list">
                                    <div class="card-body chat-fixed-search">
                                        <fieldset class="form-group position-relative has-icon-left m-0 pb-1">
                                            <input type="text" class="form-control" ng-model="search" id="iconLeft4" placeholder="Search email">
                                            <div class="form-control-position">
                                                <i class="ft-search"></i>
                                            </div>
                                        </fieldset>
                                    </div>
                                    <div id="users-list" class="list-group">

                                    </div>
                                    <div class="users-list-padding media-list" style="overflow-y: scroll;height: 600px">

                                        <a href="#" class="media border-0" ng-repeat="item in AllNotification | filter : search">
                                            <div class="media-left pr-1">
                                                <span class="avatar avatar-md">
                                                    <input type="checkbox" ng-checked="checkbox" value="{{item.id}}" /> 
                                                    <span ng-if="item.etat === 0" class="media-object rounded-circle text-circle bg-primary">O</span>
                                                    <span ng-if="item.etat === 1" class="media-object rounded-circle text-circle bg-success">O</span>
                                                </span>
                                            </div>
                                            <div class="media-body w-100">
                                                <h6 class="list-group-item-heading text-bold-500">OCM
                                                    <span class="font-small-2 float-right primary">{{item.dateNotif}}</span>
                                                </h6>
                                                <p class="list-group-item-text text-truncate mb-0 text-bold-500"><i ng-if="item.etat === 1" class="ft-check primary font-small-2"></i>{{item.titre}}</p>
                                                <p class="list-group-item-text mb-0">{{item.message}}
                                                    <span class="float-right primary"><i class="font-medium-1 ft-star blue-grey lighten-3"></i></span>

                                                </p>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
