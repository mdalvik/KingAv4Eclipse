package com.wzb.kingav.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

public class MyResponseBody extends ResponseBody {

	private ResponseBody body;
	
	File file;
	RandomAccessFile ra ;
	
	public MyResponseBody(ResponseBody body) {
		super();
		this.body = body;
		try {
			
			 ra = new RandomAccessFile("c:/text.txt","rw" );
		
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public long contentLength() {
		// TODO Auto-generated method stub
		return body.contentLength();
	}

	@Override
	public MediaType contentType() {
		// TODO Auto-generated method stub
		return body.contentType();
	}

	@Override
	public BufferedSource source() {
		// TODO Auto-generated method stub
		ForwardingSource source =  new ForwardingSource(body.source()) {

			@Override
			public long read(Buffer sink, long byteCount) throws IOException {
				// TODO Auto-generated method stub
				
				
				
				
			
				long read = super.read(sink, byteCount);
				//sink.copyTo(ra);
				
				return read; 
			}
		
		 
		};
	
		return Okio.buffer(source);
	}

}
