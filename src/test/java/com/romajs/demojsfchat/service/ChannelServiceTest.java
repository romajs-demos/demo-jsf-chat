package com.romajs.demojsfchat.service;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.romajs.demojsfchat.service.ChannelService;

@RunWith(Arquillian.class)
public class ChannelServiceTest {

	@EJB
	private ChannelService service;

	@Deployment
	public static WebArchive deploy() throws Exception {
		return ShrinkWrap.create(WebArchive.class)
				.addPackages(true, Filters.exclude(".*Test.*"), new String[] { "com.romajs.demojsfchat" })
				.addAsWebInfResource("test-persistence.xml", "persistence.xml");
		// .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
		// .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
		// .importRuntimeDependencies().resolve().withTransitivity().asFile())
	}

	@Test
	@InSequence(1)
	public void testListEmpty() {
		Assert.assertEquals(0, service.listChannels().size());
	}

	@Test
	@InSequence(2)
	public void testCreate() {
		service.create("general", null);
		Assert.assertEquals(1, service.listChannels().size());
	}

}