package io.letitroll.be.api;

public final class ApiUrls {

    public static final String PROJECTS = "/api/v1/projects";
    public static final String PROJECTS_PROJECT_FEATURES_FEATURE = "/api/v1/projects/{projectId}/features/{featureId}";
    public static final String ENVIRONMENTS_ENVIRONMENT_TARGETED_FEATURES = "/api/v1/environments/{environmentId}/targeted-features";

    private ApiUrls() {
    }
}
