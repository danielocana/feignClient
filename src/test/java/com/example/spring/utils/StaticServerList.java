package com.example.spring.utils;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

import java.util.Arrays;
import java.util.List;

public class StaticServerList<T extends Server> implements ServerList<T> {

    private final List<T> servers;

    public StaticServerList(T... servers) {
        this.servers = Arrays.asList(servers);
    }

    @Override
    public List<T> getInitialListOfServers() {
        return servers;
    }

    @Override
    public List<T> getUpdatedListOfServers() {
        return servers;
    }
}
