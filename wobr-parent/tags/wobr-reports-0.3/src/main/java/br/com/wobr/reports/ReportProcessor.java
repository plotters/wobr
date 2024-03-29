package br.com.wobr.reports;

import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 * 
 */
public interface ReportProcessor
{
	public byte[] process(Format format, ReportModel model) throws ReportProcessingException;

	public byte[] process(Format format, ReportModel model, EOQualifier qualifier) throws ReportProcessingException;

	public byte[] process(Format format, ReportModel model, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) throws ReportProcessingException;
}
