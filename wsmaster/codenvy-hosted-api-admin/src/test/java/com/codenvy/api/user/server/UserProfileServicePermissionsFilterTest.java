/*
 * Copyright (c) [2012] - [2017] Red Hat, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package com.codenvy.api.user.server;

import static com.codenvy.api.user.server.UserServicePermissionsFilter.MANAGE_USERS_ACTION;
import static com.jayway.restassured.RestAssured.given;
import static org.everrest.assured.JettyHttpServer.ADMIN_USER_NAME;
import static org.everrest.assured.JettyHttpServer.ADMIN_USER_PASSWORD;
import static org.everrest.assured.JettyHttpServer.SECURE_PATH;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.testng.Assert.assertEquals;

import com.codenvy.api.permission.server.SystemDomain;
import com.jayway.restassured.response.Response;
import org.eclipse.che.api.core.ForbiddenException;
import org.eclipse.che.api.core.rest.ApiExceptionMapper;
import org.eclipse.che.api.core.rest.shared.dto.ServiceError;
import org.eclipse.che.api.user.server.ProfileService;
import org.eclipse.che.api.user.server.UserManager;
import org.eclipse.che.api.workspace.server.WorkspaceManager;
import org.eclipse.che.commons.env.EnvironmentContext;
import org.eclipse.che.commons.subject.Subject;
import org.eclipse.che.dto.server.DtoFactory;
import org.everrest.assured.EverrestJetty;
import org.everrest.core.Filter;
import org.everrest.core.GenericContainerRequest;
import org.everrest.core.RequestFilter;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Tests for {@link UserProfileServicePermissionsFilter}
 *
 * @author Sergii Leschenko
 */
@Listeners(value = {EverrestJetty.class, MockitoTestNGListener.class})
public class UserProfileServicePermissionsFilterTest {
  @SuppressWarnings("unused")
  private static final ApiExceptionMapper MAPPER = new ApiExceptionMapper();

  @SuppressWarnings("unused")
  private static final EnvironmentFilter FILTER = new EnvironmentFilter();

  @Mock WorkspaceManager workspaceManager;
  @Mock UserManager userManager;

  UserProfileServicePermissionsFilter permissionsFilter;

  @Mock private static Subject subject;

  @Mock ProfileService service;

  @BeforeMethod
  public void setUp() {
    permissionsFilter = new UserProfileServicePermissionsFilter();
  }

  @Test
  public void shouldCheckPermissionsOnProfileUpdating() throws Exception {
    final Response response =
        given()
            .auth()
            .basic(ADMIN_USER_NAME, ADMIN_USER_PASSWORD)
            .contentType("application/json")
            .when()
            .put(SECURE_PATH + "/profile/user123/attributes");

    assertEquals(response.getStatusCode(), 204);
    verify(subject).checkPermission(SystemDomain.DOMAIN_ID, null, MANAGE_USERS_ACTION);
  }

  @Test
  public void shouldThrowExceptionWhenUserDoesNotHavePermissionsToUpdateProfile() throws Exception {
    doThrow(new ForbiddenException("User is not authorized"))
        .when(subject)
        .checkPermission(SystemDomain.DOMAIN_ID, null, MANAGE_USERS_ACTION);

    final Response response =
        given()
            .auth()
            .basic(ADMIN_USER_NAME, ADMIN_USER_PASSWORD)
            .contentType("application/json")
            .when()
            .put(SECURE_PATH + "/profile/user123/attributes");

    assertEquals(response.getStatusCode(), 403);
    assertEquals(unwrapError(response), "User is not authorized");
    verify(subject).checkPermission(SystemDomain.DOMAIN_ID, null, MANAGE_USERS_ACTION);
    verifyZeroInteractions(service);
  }

  private static String unwrapError(Response response) {
    return unwrapDto(response, ServiceError.class).getMessage();
  }

  private static <T> T unwrapDto(Response response, Class<T> dtoClass) {
    return DtoFactory.getInstance().createDtoFromJson(response.body().print(), dtoClass);
  }

  @Filter
  public static class EnvironmentFilter implements RequestFilter {
    public void doFilter(GenericContainerRequest request) {
      EnvironmentContext.getCurrent().setSubject(subject);
    }
  }
}
