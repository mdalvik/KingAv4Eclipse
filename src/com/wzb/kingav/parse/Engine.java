package com.wzb.kingav.parse;

public class Engine {
	
	private static IParseEngine iParseEngine =EngineSingle.taoBaoParseEngine;
	
	public static IParseEngine getEngine()
	{
		return iParseEngine;
	}
	
	static class  EngineSingle{
		public static TaoBaoParseEngine taoBaoParseEngine = new TaoBaoParseEngine();
		
	}

}
