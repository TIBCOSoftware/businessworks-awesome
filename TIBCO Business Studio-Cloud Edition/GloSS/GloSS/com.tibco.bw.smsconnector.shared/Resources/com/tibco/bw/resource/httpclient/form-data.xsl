<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:tns="http://www.tibco.com/Providers/Twilio/V1/Services/Call/MakeCall"
	xmlns:ns="http://www.tibco.com/xml/parser/example">
	<xsl:variable name="form-data">
		<xsl:for-each select="//tns:TwilioMakeCallRequest/*">
			<xsl:choose>
				<xsl:when test="position() = 1">
					<xsl:value-of select="concat(local-name(.),'=',.)" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="concat('&amp;',local-name(.),'=',.)" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:for-each>
	</xsl:variable>
	<xsl:template match="/">
		<ns:Test>
			<form-data>
				<xsl:copy-of select="$form-data" />
			</form-data>
		</ns:Test>
	</xsl:template>
</xsl:stylesheet>