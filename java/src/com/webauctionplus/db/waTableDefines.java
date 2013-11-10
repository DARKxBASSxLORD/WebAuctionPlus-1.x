package com.webauctionplus.db;

import com.poixson.commonjava.pxdb.dbQuery;
import com.poixson.commonjava.pxdb.TableManager.TableDAO;
import com.poixson.commonjava.pxdb.TableManager.dbTableManager;


public class waTableDefines extends dbTableManager {

	private static final String TABLE_PREFIX = "pxn_wa_";


	@Override
	public void InitTables() {
		// Auctions table
		TableDAO tableAuctions =
			defineTable("Auctions")
				.idField("auction_id")
				.addField("str",	"seller",	"32")
				.addField("dec",	"price",	"9,3")
				;
		createIfMissing(tableAuctions);
	}


	@Override
	protected dbQuery getDB() {
		return null;
	}


	@Override
	protected String getTablePrefix() {
		return TABLE_PREFIX;
	}


}
