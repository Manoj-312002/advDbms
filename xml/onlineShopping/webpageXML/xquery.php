<?php

$doc = new DOMDocument;
$doc->preserveWhiteSpace = false;
$doc->load('products.xml');
$xpath = new DOMXPath($doc);

// We start from the root element
$query = '//book/chapter/para/informaltable/tgroup/tbody/row/entry[. = "en"]';

$entries = $xpath->query($query);

foreach ($entries as $entry) {
    echo "Found {$entry->previousSibling->previousSibling->nodeValue}," .
         " by {$entry->previousSibling->nodeValue}\n";
}
?>