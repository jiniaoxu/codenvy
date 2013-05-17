/*
 *    Copyright (C) 2013 Codenvy.
 *
 */
package com.codenvy.analytics.metrics;

/**
 * @author <a href="mailto:abazko@codenvy.com">Anatoliy Bazko</a>
 */
public enum MetricType {
    WORKSPACES_CREATED_NUMBER,
    WORKSPACES_DESTROYED_NUMBER,
    TOTAL_WORKSPACES_NUMBER,
    ACTIVE_WORKSPACES_SET,
    ACTIVE_WORKSPACES_NUMBER,
    ACTIVE_WORKSPACES_PERCENT,
    USERS_CREATED_SET,
    USERS_CREATED_NUMBER,
    USERS_DESTROYED_NUMBER,
    TOTAL_USERS_NUMBER,
    ACTIVE_USERS_SET,
    ACTIVE_USERS_NUMBER,
    ACTIVE_USERS_PERCENT,
    USERS_CREATED_PROJECTS_NUMBER,
    USERS_CREATED_PROJECTS_PERCENT,
    USERS_ADDED_TO_WORKSPACE,
    USERS_ADDED_TO_WORKSPACE_FROM_WEBSITE_PERCENT,
    USERS_ADDED_TO_WORKSPACE_FROM_INVITE_PERCENT,
    USERS_SSO_LOGGED_IN,
    USERS_SSO_LOGGED_IN_USING_GOOGLE_PERCENT,
    USERS_SSO_LOGGED_IN_USING_GITHUB_PERCENT,
    USERS_SSO_LOGGED_IN_USING_FORM_PERCENT,
    PRODUCT_USAGE_TIME_TOTAL,
    PRODUCT_USAGE_USER_SESSIONS_NUMBER,
    PRODUCT_USAGE_TIME_LIST,
    PROJECTS_DESTROYED_NUMBER,
    TOTAL_PROJECTS_NUMBER,
    BUILT_PROJECTS_NUMBER,
    BUILT_PROJECTS_PERCENT,
    ACTIVE_PROJECTS_SET,
    ACTIVE_PROJECTS_NUMBER,
    ACTIVE_PROJECTS_PERCENT,
    PROJECTS_CREATED_NUMBER,
    PROJECTS_CREATED_LIST,
    PROJECTS_BUILT_NUMBER,
    PROJECTS_BUILT_LIST,
    PROJECTS_CREATED_TYPES,
    PROJECT_TYPE_JAVA_JAR_NUMBER,
    PROJECT_TYPE_JAVA_JAR_PERCENT,
    PROJECT_TYPE_JAVA_WAR_NUMBER,
    PROJECT_TYPE_JAVA_WAR_PERCENT,
    PROJECT_TYPE_JAVA_JSP_NUMBER,
    PROJECT_TYPE_JAVA_JSP_PERCENT,
    PROJECT_TYPE_JAVA_SPRING_NUMBER,
    PROJECT_TYPE_JAVA_SPRING_PERCENT,
    PROJECT_TYPE_PHP_NUMBER,
    PROJECT_TYPE_PHP_PERCENT,
    PROJECT_TYPE_PYTHON_NUMBER,
    PROJECT_TYPE_PYTHON_PERCENT,
    PROJECT_TYPE_JAVASCRIPT_NUMBER,
    PROJECT_TYPE_JAVASCRIPT_PERCENT,
    PROJECT_TYPE_RUBY_NUMBER,
    PROJECT_TYPE_RUBY_PERCENT,
    PROJECT_TYPE_MMP_NUMBER,
    PROJECT_TYPE_MMP_PERCENT,
    PROJECT_TYPE_NODEJS_NUMBER,
    PROJECT_TYPE_NODEJS_PERCENT,
    PROJECT_TYPE_ANDROID_NUMBER,
    PROJECT_TYPE_ANDROID_PERCENT,
    PROJECT_TYPE_OTHERS_NUMBER,
    PROJECT_TYPE_OTHERS_PERCENT,
    APP_DEPLOYED_LIST,
    APP_DEPLOYED_NUMBER,
    PAAS_DEPLOYMENT_TYPES,
    PAAS_DEPLOYMENT_TYPE_AWS_NUMBER,
    PAAS_DEPLOYMENT_TYPE_AWS_PERCENT,
    PAAS_DEPLOYMENT_TYPE_APPFOG_NUMBER,
    PAAS_DEPLOYMENT_TYPE_APPFOG_PERCENT,
    PAAS_DEPLOYMENT_TYPE_CLOUDBEES_NUMBER,
    PAAS_DEPLOYMENT_TYPE_CLOUDBEES_PERCENT,
    PAAS_DEPLOYMENT_TYPE_CLOUDFOUNDRY_NUMBER,
    PAAS_DEPLOYMENT_TYPE_CLOUDFOUNDRY_PERCENT,
    PAAS_DEPLOYMENT_TYPE_GAE_NUMBER,
    PAAS_DEPLOYMENT_TYPE_GAE_PERCENT,
    PAAS_DEPLOYMENT_TYPE_HEROKU_NUMBER,
    PAAS_DEPLOYMENT_TYPE_HEROKU_PERCENT,
    PAAS_DEPLOYMENT_TYPE_OPENSHIFT_NUMBER,
    PAAS_DEPLOYMENT_TYPE_OPENSHIFT_PERCENT,
    PAAS_DEPLOYMENT_TYPE_TIER3_NUMBER,
    PAAS_DEPLOYMENT_TYPE_TIER3_PERCENT,
    PAAS_DEPLOYMENT_TYPE_LOCAL_NUMBER,
    PAAS_DEPLOYMENT_TYPE_LOCAL_PERCENT,
    JREBEL_ELIGIBLE,
    JREBEL_USAGE,
    JREBEL_USAGE_PERCENT,
    INVITATIONS_SENT,
    INVITATIONS_ACCEPTED_PERCENT
}
