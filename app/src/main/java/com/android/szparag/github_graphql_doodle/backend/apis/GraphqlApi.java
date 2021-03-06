package com.android.szparag.github_graphql_doodle.backend.apis;

import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLBaseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLResponseObject;

import graphql.schema.GraphQLObjectType;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ciemek on 05/11/2016.
 */

public interface GraphqlApi {

    @POST("graphql")
    Call<GraphQLResponseObject<GraphQLBaseObject>> getGraphData(@Body GraphQLObjectType query);

    @POST("graphql")
    Call<GraphQLResponseObject<RepositoryOwner>> getRepositoryOwner(@Body GraphQLObjectType query);

    @POST("graphql")
    Call<GraphQLResponseObject<Repository>> getRepository(@Body GraphQLObjectType query);

}
