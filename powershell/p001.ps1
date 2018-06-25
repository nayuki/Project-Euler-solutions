$c=0
foreach ($i in (1..999)){
    if ($i%5 -eq 0){
        $c=$c+$i
        }
    elseif($i%3 -eq 0){
        $c=$c+$i
        }
        }
Write-Host $c