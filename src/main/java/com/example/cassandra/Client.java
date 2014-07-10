package com.example.cassandra;

import com.datastax.driver.core.*;

public class Client {

	private Cluster cluster;
	private Session session;

	public Client(String node) {
		connect( node );
	}

	private void connect(String node) {
		cluster = Cluster.builder()
		    .addContactPoint(node)
		    .build();

		session = cluster.connect();
	}

	public ResultSet execute( String cql3 ) {
		return session.execute( cql3 );
	}

	public void close() {
	  cluster.close();
	}

}