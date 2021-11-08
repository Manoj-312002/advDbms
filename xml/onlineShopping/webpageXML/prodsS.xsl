<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
    <html>
    <link rel="stylesheet" href="styles.css"></link>
    <body>
        <h1>List of prooducts sorted by name</h1>

        <xsl:for-each select="products/product" >
            <xsl:sort select="name" />
        	<xsl:if test="qty>0">
				<div class="line">
					<label> <xsl:value-of select="name"/>   -  <xsl:value-of select="cost"/> </label>
					<p> Quantity :  <xsl:value-of select="qty"/>   -  likes : <xsl:value-of select="likes"/>  </p>
					<label class="labels"> reviews </label>
					<xsl:for-each select="reviews/review" >
						<p><xsl:value-of select="."/></p>
					</xsl:for-each>		
				</div>
            </xsl:if> 
        </xsl:for-each>
        
		<h1> Other xpath queries </h1>
		<div class="line"> 
			<label> First three products </label>
			<xsl:for-each select="products/product[position()&lt;4]" >
				<p> <xsl:value-of select="name"/>   -  <xsl:value-of select="qty"/>  </p>
			</xsl:for-each>
		</div>
		<div class="line"> 
			<label> All name alone </label>
			<xsl:for-each select="products/product/name" >
				<p><xsl:value-of select="."/></p>
			</xsl:for-each>
		</div>
		<div class="line"> 
			<label> 2nd product quantity </label>
			<p><xsl:value-of select="products/product[2]/qty"/></p>
		</div>
    </body>
    </html>
</xsl:template>
</xsl:stylesheet>