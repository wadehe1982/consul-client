package com.xxx.consulclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsulClientController {

	@Autowired
	private LoadBalancerClient loadBalancer;

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/discover")
	public Object discover() {
		ServiceInstance instance = loadBalancer.choose("consul-server");
		String result = instance.getUri().toString();
		return result;
	}

	@RequestMapping("/services")
	public Object services() {
		List<ServiceInstance> result = discoveryClient.getInstances("consul-server");
		List<String> serviceIds = discoveryClient.getServices();
		System.out.println("serviceIds is:" + serviceIds);
		return result;
	}
}
