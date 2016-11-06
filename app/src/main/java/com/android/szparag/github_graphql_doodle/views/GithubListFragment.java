package com.android.szparag.github_graphql_doodle.views;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.szparag.github_graphql_doodle.R;
import com.android.szparag.github_graphql_doodle.adapters.RepositoriesViewAdapter;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlNodeObject;
import com.android.szparag.github_graphql_doodle.dagger.MainComponent;
import com.android.szparag.github_graphql_doodle.decorators.HorizontalSeparator;
import com.android.szparag.github_graphql_doodle.presenters.contracts.GithubListBasePresenter;
import com.android.szparag.github_graphql_doodle.utils.Utils;
import com.android.szparag.github_graphql_doodle.views.contracts.GithubListView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class GithubListFragment extends Fragment implements GithubListView {


    //todo: remove graphql-java library (not used)

    @BindView(R.id.recycler_repositories)
    RecyclerView            repositoriesView;
    RepositoriesViewAdapter repositoriesAdapter;

    @BindView(R.id.repositoryowner_front)
    View                    repositoryOwnerView;

    @Inject
    GithubListBasePresenter presenter;

    private Unbinder        unbinder;


    //todo: newInstance here!
    public GithubListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainComponent dagger = Utils.getDagger2(this);
        dagger.inject(this);
        presenter.setView(this, dagger);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, layout);
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buildRepositoriesListView();
        buildRepositoryOwnerView();
        hideRepositoryOwnerView();
        hideRepositoriesListView();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.fetchData();
    }

    @Override
    public void onResume() {
        super.onResume();
//        GraphQLObjectType queryType = newObject()
//                .name("helloWorldQuery")
//                .field(newFieldDefinition()
//                        .type(GraphQLString)
//                        .name("hello")
//                        .staticValue("world"))
//                .build();
//
//        GraphQLSchema schema = GraphQLSchema.newSchema()
//                .query(queryType)
//                .build();


//        Map<String, Object> result = (Map<String, Object>) new GraphQL(schema).execute("{hello}").getData();

//        GsonConverterFactory

//        textView.setText(result.toString());

//        GraphQLObjectType queryType2 = newObject()
//                .name("repositoryOwner")
//                .field(newFieldDefinition()
//                        .type(GraphQLString)
//                        .name("login"))
//                .field(newFieldDefinition()
//                        .type(GraphQLString)
//                        .name("avatarURL"))
//                .build();
//
//        GraphQLSchema graphQLSchema2 = GraphQLSchema.newSchema()
//                .query(queryType2)
//                .build();

////        textView.setText(queryType2.toString());
//        textView2.setText(graphQLSchema2.getAllTypesAsList().toString());
////        textView3.setText(graphQLSchema2.getDictionary().toString());
        RepositoryOwner repositoryOwner = new RepositoryOwner("repositoryOwner", true, "ReactiveX");

//        service.getGraphData(repositoryOwner, new Callback<GraphqlResponseObject>() {
//                    @Override
//                    public void onResponse(Call<GraphqlResponseObject> call, Response<GraphqlResponseObject> response) {
//                        GraphqlResponseObject obj = response.body();
//                    }
//
//                    @Override
//                    public void onFailure(Call<GraphqlResponseObject> call, Throwable t) {
//                    }
//                }
//        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void showGithubFetchSuccess() {
        Snackbar.make(getView(), getString(R.string.data_fetch_success), Snackbar.LENGTH_LONG);
    }

    @Override
    public void showGithubFetchFailure() {
        Snackbar.make(getView(), getString(R.string.data_fetch_failure), Snackbar.LENGTH_LONG);
    }

    @Override
    public void showNetworkConnectionFailure() {
        Snackbar.make(getView(), getString(R.string.network_connection_failure), Snackbar.LENGTH_INDEFINITE);
    }

    @Override
    public void buildRepositoryOwnerView() {

    }

    @Override
    public void hideRepositoryOwnerView() {
        repositoryOwnerView.setVisibility(View.GONE); //todo: animations on show / hide view
    }

    @Override
    public void showRepositoryOwnerView() {
        repositoryOwnerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateRepositoryOwnerView(RepositoryOwner repositoryOwner) {
        showRepositoryOwnerView();
    }

    @Override
    public void buildRepositoriesListView() {
        //todo: snapping!
        repositoriesView.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        repositoriesView.addItemDecoration(new HorizontalSeparator(getContext()));
        new LinearSnapHelper().attachToRecyclerView(repositoriesView);
        repositoriesAdapter = new RepositoriesViewAdapter(null);
        repositoriesView.setAdapter(repositoriesAdapter);
        repositoriesView.setNestedScrollingEnabled(false); //todo: ...false?
    }

    @Override
    public void hideRepositoriesListView() {
        repositoriesView.setVisibility(View.GONE);
    }

    @Override
    public void showRepositoriesListView() {
        repositoriesView.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateRepositoriesListView(List<GraphqlNodeObject> repositories) {
        showRepositoriesListView();
        repositoriesAdapter.updateItems(repositories);

    }
}
