package com.android.szparag.github_graphql_doodle.views.contracts;

import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;

import java.util.List;

/**
 * Created by ciemek on 05/11/2016.
 */

public interface GithubListView {

    void showNetworkConnectionFailure();

    void showGithubFetchSuccess();
    void showGithubFetchFailure();

    void showGithubApiKeyFailure();

    void buildRepositoryOwnerView();
    void hideRepositoryOwnerView();
    void showRepositoryOwnerView();
    void updateRepositoryOwnerView(RepositoryOwner repositoryOwner);

    void buildRepositoriesListView();
    void hideRepositoriesListView();
    void showRepositoriesListView();
    void updateRepositoriesListView(List<Repository> repositories);


    void registerInternetConnectivityListener();
    void unregisterInternetConnectivityListener();
    boolean getInternetConnectivity();
}
