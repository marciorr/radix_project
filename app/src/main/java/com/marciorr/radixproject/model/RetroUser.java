package com.marciorr.radixproject.model;

import com.google.gson.annotations.SerializedName;

public class RetroUser {

    @SerializedName("stargazers_count")
    private Integer stargazers_count;
    @SerializedName("forks_count")
    private Integer forks_count;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("open_issues_count")
    private Integer open_issues_count;
    @SerializedName("language")
    private String language;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("html_url")
    private String html_url;

    //Inicializa as variáveis
    public RetroUser(Integer stargazers_count, Integer forks_count, Integer open_issues_count, String name, String description, String html_url) {
        this.stargazers_count = stargazers_count;
        this.forks_count = forks_count;
        this.created_at = created_at;
        this.open_issues_count = open_issues_count;
        this.language = language;
        this.name = name;
        this.description = description;
        this.html_url = html_url;
    }

    //Cria os getters e setters
    public Integer getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(Integer stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public Integer getForks_count() {
        return forks_count;
    }

    public void setForks_count(Integer forks_count) {
        this.forks_count = forks_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Integer getOpen_issues_count() {
        return open_issues_count;
    }

    public void setOpen_issues_count(Integer open_issues_count) {
        this.open_issues_count = open_issues_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
