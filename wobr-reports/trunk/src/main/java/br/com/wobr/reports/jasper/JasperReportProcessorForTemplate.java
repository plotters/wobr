package br.com.wobr.reports.jasper;

import java.net.URL;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import br.com.wobr.reports.AbstractReportProcessor;
import br.com.wobr.reports.Format;
import br.com.wobr.reports.ReportModel;
import br.com.wobr.reports.ReportProcessingException;

import com.google.inject.Inject;
import com.webobjects.foundation.NSMutableArray;

/**
 * @author <a href="mailto:alexandre.parreira@doit.com.br">Alexandre
 *         Parreira</a>
 */
public class JasperReportProcessorForTemplate extends AbstractReportProcessor
{
	@Inject
	public JasperReportProcessorForTemplate()
	{
	}

	@Override
	protected byte[] handleProcessing( final Format format, final ReportModel model, final Map<String, Object> parameters, final JRDataSource dataSource ) throws ReportProcessingException
	{
		byte[] data = null;

		JasperPrint print = null;

		URL url = model.templateLocation();

		if( url == null )
		{
			return null;
		}

		try
		{
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject( url );

			JRField[] fields = jasperReport.getFields();

			NSMutableArray<String> keypaths = new NSMutableArray<String>();

			for( JRField field : fields )
			{
				keypaths.add( field.getName() );
			}

			print = JasperFillManager.fillReport( jasperReport, parameters, dataSource );

			data = JasperExportManager.exportReportToPdf( print );

		}
		catch( JRException exception )
		{
			new ReportProcessingException( exception );
		}

		return data;
	}
}
